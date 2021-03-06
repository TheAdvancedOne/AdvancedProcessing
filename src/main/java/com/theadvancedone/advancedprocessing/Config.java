package com.theadvancedone.advancedprocessing;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

public class Config {
    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_ENERGY = "energy";
    public static final String SUBCATEGORY_FIRSTBLOCK = "firstblock";

    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue FIRSTBLOCK_MAXPOWER;
    public static ForgeConfigSpec.IntValue FIRSTBLOCK_GENERATE;
    public static ForgeConfigSpec.IntValue FIRSTBLOCK_SEND;
    public static ForgeConfigSpec.IntValue FIRSTBLOCK_TICKS;

    static {
        COMMON_BUILDER.comment("General Settings").push(CATEGORY_GENERAL);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Power Settings").push(CATEGORY_ENERGY);
        setupFirstBlockConfig();
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupFirstBlockConfig() {
        COMMON_BUILDER.comment("Firstblock settings").push(SUBCATEGORY_FIRSTBLOCK);

        FIRSTBLOCK_MAXPOWER = COMMON_BUILDER.comment("Maximum power for the Firstblock generator")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_GENERATE = COMMON_BUILDER.comment("Power generated per tick")
                .defineInRange("generate", 50, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_SEND = COMMON_BUILDER.comment("Maximum power to send per side per tick")
                .defineInRange("send", 100, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_TICKS = COMMON_BUILDER.comment("Ticks per dimamonds")
                .defineInRange("ticks", 20, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {

    }

}
