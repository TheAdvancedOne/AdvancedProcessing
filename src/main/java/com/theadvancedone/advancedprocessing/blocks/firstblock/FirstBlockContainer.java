package com.theadvancedone.advancedprocessing.blocks.firstblock;

import com.theadvancedone.advancedprocessing.blocks.BasicContainer;
import com.theadvancedone.advancedprocessing.misc.Coord;
import com.theadvancedone.advancedprocessing.misc.ItemSlot;
import com.theadvancedone.advancedprocessing.setup.Registration;
import com.theadvancedone.advancedprocessing.tools.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.Arrays;

public class FirstBlockContainer extends BasicContainer<FirstBlock> {

    public FirstBlockContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(windowId, world, pos, playerInventory, player, Arrays.asList(new Coord(0, 64, 24)), new Coord(10, 70, 0), Registration.FIRSTBLOCK_CONTAINER.get(), Registration.FIRSTBLOCK.get(), Arrays.asList(new ItemSlot(0, Arrays.asList(Items.DIAMOND))));
        TileEntity tileEntity = super.getTileEntity();
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getEnergy();
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(e -> ((CustomEnergyStorage) e).setEnergy(value));
            }
        });
    }

    public int getEnergy() {
        return super.getTileEntity().getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }
}
