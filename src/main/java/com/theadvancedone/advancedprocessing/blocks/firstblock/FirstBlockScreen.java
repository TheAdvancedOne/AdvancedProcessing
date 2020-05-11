package com.theadvancedone.advancedprocessing.blocks.firstblock;

import com.theadvancedone.advancedprocessing.blocks.BasicScreen;
import com.theadvancedone.advancedprocessing.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.theadvancedone.advancedprocessing.AdvancedProcessing.MODID;

public class FirstBlockScreen extends BasicScreen<FirstBlockContainer> {

    public FirstBlockScreen(FirstBlockContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name, new ResourceLocation(MODID, "gui/" + Registration.FIRSTBLOCK.get().getRegistryName().getPath() + ".png"));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 10, 10, 0xffffff);
    }
}
