package dev.photoncore.server.init;

import dev.photoncore.server.blocks.handlers.*;
import dev.photoncore.server.blocks.placements.*;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.instance.block.BlockManager;

public class Blocks {
    public static void init() {
        BlockManager blockManager = MinecraftServer.getBlockManager();
        
        // Handlers
        registerHandler(new CraftingTableHandler());
        registerHandler(new FurnaceHandler());
        
        registerHandler(new StrippedHandler(Block.ACACIA_LOG.namespace()));
        registerHandler(new StrippedHandler(Block.OAK_LOG.namespace()));
        registerHandler(new StrippedHandler(Block.BIRCH_LOG.namespace()));
        registerHandler(new StrippedHandler(Block.CHERRY_LOG.namespace()));
        registerHandler(new StrippedHandler(Block.JUNGLE_LOG.namespace()));
        registerHandler(new StrippedHandler(Block.DARK_OAK_LOG.namespace()));
        registerHandler(new StrippedHandler(Block.MANGROVE_LOG.namespace()));
        registerHandler(new StrippedHandler(Block.SPRUCE_LOG.namespace()));
        
        registerHandler(new StrippedHandler(Block.ACACIA_WOOD.namespace()));
        registerHandler(new StrippedHandler(Block.OAK_WOOD.namespace()));
        registerHandler(new StrippedHandler(Block.BIRCH_WOOD.namespace()));
        registerHandler(new StrippedHandler(Block.CHERRY_WOOD.namespace()));
        registerHandler(new StrippedHandler(Block.JUNGLE_WOOD.namespace()));
        registerHandler(new StrippedHandler(Block.DARK_OAK_WOOD.namespace()));
        registerHandler(new StrippedHandler(Block.MANGROVE_WOOD.namespace()));
        registerHandler(new StrippedHandler(Block.SPRUCE_WOOD.namespace()));
        
        registerHandler(new TrapdoorHandler(Block.OAK_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.ACACIA_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.BAMBOO_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.BIRCH_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.CHERRY_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.CRIMSON_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.DARK_OAK_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.JUNGLE_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.MANGROVE_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.SPRUCE_TRAPDOOR.namespace()));
        registerHandler(new TrapdoorHandler(Block.WARPED_TRAPDOOR.namespace()));
        
        registerHandler(new GateHandler(Block.OAK_FENCE_GATE.namespace()));
        
        // Placements
        
        // Gates
        blockManager.registerBlockPlacementRule(new GatePlacementRule(Block.OAK_FENCE_GATE));
        
        // Trapdoors
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.OAK_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.ACACIA_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.BAMBOO_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.BIRCH_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.CHERRY_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.CRIMSON_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.DARK_OAK_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.JUNGLE_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.MANGROVE_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.SPRUCE_TRAPDOOR));
        blockManager.registerBlockPlacementRule(new TrapdoorPlacementRule(Block.WARPED_TRAPDOOR));
        
        // Horizontal (Furnace, Grazed Terracotta and Nest)
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.FURNACE));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.BLAST_FURNACE));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.SMOKER));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.BEEHIVE));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.BEE_NEST));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.LOOM));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.CARVED_PUMPKIN));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.JACK_O_LANTERN));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.DAMAGED_ANVIL));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.CHISELED_BOOKSHELF));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.LECTERN));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.VAULT));
        //blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.ENDER_CHEST));
        
        // Gates
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.ACACIA_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.BAMBOO_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.BIRCH_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.CHERRY_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.CRIMSON_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.DARK_OAK_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.JUNGLE_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.MANGROVE_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.OAK_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.SPRUCE_FENCE_GATE));
        //blockManager.registerBlockPlacementRule(new WaterloggedHorizontalPlacementRule(Block.WARPED_FENCE_GATE));

        // Axis (Logs, Woods, Basalt and Pillars)
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.OAK_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.ACACIA_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.OAK_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.BIRCH_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.CHERRY_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.JUNGLE_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.DARK_OAK_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.MANGROVE_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.SPRUCE_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.WARPED_STEM));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.CRIMSON_STEM));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.BAMBOO_BLOCK));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_ACACIA_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_OAK_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_BIRCH_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_CHERRY_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_JUNGLE_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_DARK_OAK_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_MANGROVE_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_SPRUCE_LOG));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_WARPED_STEM));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_CRIMSON_STEM));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_BAMBOO_BLOCK));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.ACACIA_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.OAK_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.BIRCH_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.CHERRY_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.JUNGLE_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.DARK_OAK_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.MANGROVE_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.SPRUCE_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.WARPED_HYPHAE));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.CRIMSON_HYPHAE));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_ACACIA_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_OAK_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_BIRCH_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_CHERRY_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_JUNGLE_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_DARK_OAK_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_MANGROVE_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_SPRUCE_WOOD));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_WARPED_HYPHAE));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.STRIPPED_CRIMSON_HYPHAE));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.POLISHED_BASALT));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.BASALT));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.PURPUR_PILLAR));
        blockManager.registerBlockPlacementRule(new FacePlacementRule(Block.QUARTZ_PILLAR));
        //blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.BONE_BLOCK));

        // Wall
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.DEEPSLATE_BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.POLISHED_BLACKSTONE_BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.SANDSTONE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.RED_NETHER_BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.STONE_BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.POLISHED_DEEPSLATE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.TUFF_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.POLISHED_BLACKSTONE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.MOSSY_COBBLESTONE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.DEEPSLATE_TILE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.BLACKSTONE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.DIORITE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.PRISMARINE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.END_STONE_BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.TUFF_BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.COBBLED_DEEPSLATE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.GRANITE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.NETHER_BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.MOSSY_STONE_BRICK_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.COBBLESTONE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.POLISHED_TUFF_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.ANDESITE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.RED_SANDSTONE_WALL));
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.MUD_BRICK_WALL));
        
        // Panels
        //blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.IRON_BARS));
        
        // Fences
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.ACACIA_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.CRIMSON_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.BAMBOO_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.DARK_OAK_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.BIRCH_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.OAK_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.MANGROVE_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.SPRUCE_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.JUNGLE_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.WARPED_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.CHERRY_FENCE));
        blockManager.registerBlockPlacementRule(new FencePlacementRule(Block.NETHER_BRICK_FENCE)); // TODO: Don't connect with others
        
        // Slabs
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.POLISHED_DIORITE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.EXPOSED_CUT_COPPER_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.POLISHED_TUFF_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.WAXED_CUT_COPPER_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.ANDESITE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.DARK_PRISMARINE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.BAMBOO_MOSAIC_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.RED_SANDSTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.MANGROVE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.MUD_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.OAK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.DEEPSLATE_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.JUNGLE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.POLISHED_BLACKSTONE_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.CUT_COPPER_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.POLISHED_GRANITE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.CRIMSON_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.SMOOTH_RED_SANDSTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.SANDSTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.RED_NETHER_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.STONE_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.TUFF_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.POLISHED_DEEPSLATE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.STONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.POLISHED_ANDESITE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.PRISMARINE_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.OXIDIZED_CUT_COPPER_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.POLISHED_BLACKSTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.WARPED_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.MOSSY_COBBLESTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.SPRUCE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.DEEPSLATE_TILE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.BLACKSTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.ACACIA_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.CUT_SANDSTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.SMOOTH_QUARTZ_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.DIORITE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.PRISMARINE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.BAMBOO_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.CUT_RED_SANDSTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.WAXED_EXPOSED_CUT_COPPER_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.QUARTZ_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.END_STONE_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.PETRIFIED_OAK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.TUFF_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.COBBLED_DEEPSLATE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.DARK_OAK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.GRANITE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.SMOOTH_SANDSTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.NETHER_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.MOSSY_STONE_BRICK_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.BIRCH_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.COBBLESTONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.WAXED_WEATHERED_CUT_COPPER_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.WAXED_OXIDIZED_CUT_COPPER_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.PURPUR_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.SMOOTH_STONE_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.CHERRY_SLAB));
        blockManager.registerBlockPlacementRule(new SlabPlacementRule(Block.WEATHERED_CUT_COPPER_SLAB));
    }

    private static void registerHandler(BlockHandler blockHandler) {
        MinecraftServer.getBlockManager().registerHandler(blockHandler.getNamespaceId(), () -> blockHandler);
    }
}
