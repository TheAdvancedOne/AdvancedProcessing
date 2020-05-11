package com.theadvancedone.advancedprocessing.blocks.firstblock;

import com.theadvancedone.advancedprocessing.blocks.BasicMachine;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class FirstBlock extends BasicMachine {

    public FirstBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2.0f));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FirstBlockTile();
    }


}