package com.theadvancedone.advancedprocessing.setup;

import com.theadvancedone.advancedprocessing.AdvancedProcessing;
import com.theadvancedone.advancedprocessing.blocks.firstblock.FirstBlockScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AdvancedProcessing.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(Registration.FIRSTBLOCK_CONTAINER.get(), FirstBlockScreen::new);
    }
}
