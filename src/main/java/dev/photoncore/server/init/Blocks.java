package dev.photoncore.server.init;

import dev.photoncore.server.blocks.handlers.CraftingTableHandler;
import dev.photoncore.server.blocks.handlers.FurnaceHandler;
import dev.photoncore.server.blocks.handlers.StrippedHandler;
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
        
        // Placement rules
        blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.FURNACE));
    }

    private static void registerHandler(BlockHandler blockHandler) {
        MinecraftServer.getBlockManager().registerHandler(blockHandler.getNamespaceId(), () -> blockHandler);
    }
}
