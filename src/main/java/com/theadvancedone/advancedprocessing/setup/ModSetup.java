package com.theadvancedone.advancedprocessing.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModSetup {
    public static final ItemGroup ITEM_GROUP = new ItemGroup("advancedprocessing") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.FIRSTBLOCK.get());
        }
    };

    public static void init(final FMLClientSetupEvent event) {

    }
}
