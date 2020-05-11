package com.theadvancedone.advancedprocessing.setup;

import com.theadvancedone.advancedprocessing.AdvancedProcessing;
import com.theadvancedone.advancedprocessing.blocks.firstblock.FirstBlock;
import com.theadvancedone.advancedprocessing.blocks.firstblock.FirstBlockContainer;
import com.theadvancedone.advancedprocessing.blocks.firstblock.FirstBlockTile;
import com.theadvancedone.advancedprocessing.items.FirstItem;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.theadvancedone.advancedprocessing.AdvancedProcessing.MODID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<FirstBlock> FIRSTBLOCK = BLOCKS.register("firstblock", FirstBlock::new);
    public static final RegistryObject<Item> FIRSTBLOCK_ITEM = ITEMS.register("firstblock", () -> new BlockItem(FIRSTBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<FirstBlockTile>> FIRSTBLOCK_TILE = TILES.register("firstblock", () -> TileEntityType.Builder.create(FirstBlockTile::new, FIRSTBLOCK.get()).build(null));
    public static final RegistryObject<ContainerType<FirstBlockContainer>> FIRSTBLOCK_CONTAINER = CONTAINERS.register("firstblock", () -> IForgeContainerType.create(((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new FirstBlockContainer(windowId, AdvancedProcessing.proxy.getClientWorld(), pos, inv, AdvancedProcessing.proxy.getClientPlayer());
    })));

    public static final RegistryObject<Item> FIRSTITEM = ITEMS.register("firstitem", FirstItem::new);
}
