package com.theadvancedone.advancedprocessing.items;

import com.theadvancedone.advancedprocessing.setup.ModSetup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FirstItem extends Item {
    public FirstItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(ModSetup.ITEM_GROUP));
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (world.isRemote && context.getHand() == Hand.MAIN_HAND) {
            ItemStack itemStack = new ItemStack(world.getBlockState(pos).getBlock().asItem(), 1);

            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }
    }
}
