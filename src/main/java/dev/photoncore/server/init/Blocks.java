package dev.photoncore.server.init;

import dev.photoncore.server.blocks.handlers.CraftingTableHandler;
import dev.photoncore.server.blocks.handlers.FurnaceHandler;
import dev.photoncore.server.blocks.placements.HorizontalPlacementRule;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.instance.block.BlockManager;

import java.util.Objects;

public class Blocks {
    public static void init() {
        BlockManager blockManager = MinecraftServer.getBlockManager();
        
        // Block handlers
        registerHandler(Block.CRAFTING_TABLE, new CraftingTableHandler());
        registerHandler(Block.FURNACE, new FurnaceHandler());
        
        // Placement rules
        blockManager.registerBlockPlacementRule(new HorizontalPlacementRule(Block.FURNACE));
    }

    private static void registerHandler(Block block, BlockHandler blockHandler) {
        MinecraftServer.getBlockManager().registerHandler(block.namespace(), () -> Objects.requireNonNull(block.withHandler(blockHandler).handler()));
    }
}
