package com.theadvancedone.advancedprocessing.blocks;

import com.theadvancedone.advancedprocessing.misc.Coord;
import com.theadvancedone.advancedprocessing.misc.ItemSlot;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.List;

public class BasicContainer<T extends Block> extends Container {

    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;
    private T registryBlock;
    private List<ItemSlot> listItemsInSlots;

    public BasicContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, List<Coord> slotCoord, Coord inventorySlot, ContainerType<?> containerType, T entryBlock, List<ItemSlot> listItemsInSlots) {

        super(containerType, windowId);
        this.tileEntity = world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.registryBlock = entryBlock;
        this.listItemsInSlots = listItemsInSlots;

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            for (Coord coord : slotCoord) {
                addSlot(new SlotItemHandler(h, coord.getX(), coord.getY(), coord.getZ()));
            }
        });

        layoutPlayerInventorySlots(inventorySlot.getX(), inventorySlot.getY());
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, registryBlock);
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        int nSlots = this.listItemsInSlots.size();
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();
            for (ItemSlot itemSlot : this.listItemsInSlots) {
                if (index == itemSlot.getIndex()) {
                    if (!this.mergeItemStack(stack, nSlots, nSlots + 36, true)) {
                        return ItemStack.EMPTY;
                    }
                    slot.onSlotChange(stack, itemStack);
                } else {
                    if (itemSlot.getItemList().contains(stack.getItem())) {
                        if (!this.mergeItemStack(stack, itemSlot.getIndex(), itemSlot.getIndex() + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= nSlots && index < nSlots + 27) {
                        if (!this.mergeItemStack(stack, nSlots + 27, nSlots + 36, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < nSlots + 27 + 9) {
                        if (!this.mergeItemStack(stack, nSlots, nSlots + 27, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }
        return itemStack;
    }
}
