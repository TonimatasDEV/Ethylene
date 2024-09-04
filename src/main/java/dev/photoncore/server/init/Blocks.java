package dev.photoncore.server.init;

import dev.photoncore.server.blocks.handlers.CraftingTableHandler;
import dev.photoncore.server.blocks.handlers.FurnaceHandler;
import dev.photoncore.server.blocks.handlers.StrippedHandler;
import dev.photoncore.server.blocks.placements.AxisPlacementRule;
import dev.photoncore.server.blocks.placements.ConnectedPlacementRule;
import dev.photoncore.server.blocks.placements.HorizontalPlacementRule;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.instance.block.BlockManager;

public class Blocks {
    public static void init() {
        BlockManager blockManager = MinecraftServer.getBlockManager();
        
        // Block handlers
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
        
        // Horizontal (Furnace, Grazed Terracotta and Nest)
        blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.FURNACE));

        // Axis (Logs, Woods, Basalt and Pillars)
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.OAK_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.ACACIA_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.OAK_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.BIRCH_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.CHERRY_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.JUNGLE_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.DARK_OAK_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.MANGROVE_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.SPRUCE_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.WARPED_STEM));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.CRIMSON_STEM));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.BAMBOO_BLOCK));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_ACACIA_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_OAK_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_BIRCH_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_CHERRY_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_JUNGLE_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_DARK_OAK_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_MANGROVE_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_SPRUCE_LOG));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_WARPED_STEM));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_CRIMSON_STEM));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_BAMBOO_BLOCK));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.ACACIA_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.OAK_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.BIRCH_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.CHERRY_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.JUNGLE_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.DARK_OAK_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.MANGROVE_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.SPRUCE_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.WARPED_HYPHAE));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.CRIMSON_HYPHAE));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_ACACIA_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_OAK_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_BIRCH_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_CHERRY_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_JUNGLE_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_DARK_OAK_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_MANGROVE_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_SPRUCE_WOOD));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_WARPED_HYPHAE));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.STRIPPED_CRIMSON_HYPHAE));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.POLISHED_BASALT));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.BASALT));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.PURPUR_PILLAR));
        blockManager.registerBlockPlacementRule(new AxisPlacementRule(Block.QUARTZ_PILLAR));

        // Connected (Fences, Walls, Bars and Panels)
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.DEEPSLATE_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.POLISHED_BLACKSTONE_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.ACACIA_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.SANDSTONE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.RED_NETHER_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.STONE_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.POLISHED_DEEPSLATE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.TUFF_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.CRIMSON_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.BAMBOO_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.POLISHED_BLACKSTONE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.MOSSY_COBBLESTONE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.DEEPSLATE_TILE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.BLACKSTONE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.DARK_OAK_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.BIRCH_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.DIORITE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.PRISMARINE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.OAK_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.END_STONE_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.NETHER_BRICK_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.TUFF_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.MANGROVE_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.SPRUCE_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.COBBLED_DEEPSLATE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.GRANITE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.JUNGLE_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.NETHER_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.MOSSY_STONE_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.COBBLESTONE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.WARPED_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.POLISHED_TUFF_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.ANDESITE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.CHERRY_FENCE));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.RED_SANDSTONE_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.MUD_BRICK_WALL));
        blockManager.registerBlockPlacementRule(new ConnectedPlacementRule(Block.IRON_BARS));
    }

    private static void registerHandler(BlockHandler blockHandler) {
        MinecraftServer.getBlockManager().registerHandler(blockHandler.getNamespaceId(), () -> blockHandler);
    }
}
