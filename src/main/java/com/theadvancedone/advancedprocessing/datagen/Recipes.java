package com.theadvancedone.advancedprocessing.datagen;

import com.theadvancedone.advancedprocessing.setup.Registration;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(Registration.FIRSTBLOCK.get())
                .patternLine("xxx")
                .patternLine("x#x")
                .patternLine("xxx")
                .key('x', Blocks.COBBLESTONE)
                .key('#', Tags.Items.DYES_RED)
                .setGroup("advancedprocessing")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(Registration.FIRSTITEM.get())
                .patternLine("xxx")
                .patternLine(" # ")
                .patternLine(" # ")
                .key('x', Registration.FIRSTBLOCK.get())
                .key('#', Tags.Items.RODS_WOODEN)
                .setGroup("advancedprocessing")
                .addCriterion("firstblock", InventoryChangeTrigger.Instance.forItems(Registration.FIRSTBLOCK.get()))
                .build(consumer);
    }
}
