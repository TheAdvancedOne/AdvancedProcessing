package com.theadvancedone.advancedprocessing.jei;

import com.theadvancedone.advancedprocessing.AdvancedProcessing;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class AdvancedProcessingJEI implements IModPlugin {
    public static IJeiRuntime runtime;

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(AdvancedProcessing.MODID, "general");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        runtime = jeiRuntime;
    }

    /*public void test(ItemStack itemStack) {
        IFocus focus = runtime.getRecipeManager().createFocus(IFocus.Mode.INPUT, itemStack);
        List<IRecipeCategory> list = runtime.getRecipeManager().getRecipeCategories(focus);
        List<Object> catList;
        IRecipeCategory cat = null;
        List<Object> recipes = null;
        outerloop:
        for (IRecipeCategory category : list) {
            catList = runtime.getRecipeManager().getRecipeCatalysts(category);
            for (Object ingredient : catList) {
                if (ItemStack.areItemStacksEqual((ItemStack) ingredient, itemStack)) {
                    cat = category;
                    break outerloop;
                }
            }
        }
        try {
            recipes = runtime.getRecipeManager().getRecipes(cat);
        } catch (Exception e) {
            return;
        }
    }*/
}
