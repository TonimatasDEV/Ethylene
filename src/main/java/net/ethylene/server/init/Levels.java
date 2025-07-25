package net.ethylene.server.init;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.DimensionType;

public class Levels {
    public static InstanceContainer OVERWORLD;

    public static void init() {
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();

        // Overworld
        OVERWORLD = instanceManager.createInstanceContainer(DimensionType.OVERWORLD);

        OVERWORLD.setChunkLoader(new AnvilLoader("world"));
        OVERWORLD.setChunkSupplier(LightingChunk::new);
        OVERWORLD.setGenerator(unit -> {
            unit.modifier().fillHeight(-61, -60, Block.GRASS_BLOCK);
            unit.modifier().fillHeight(-63, -61, Block.DIRT);
            unit.modifier().fillHeight(-64, -63, Block.BEDROCK);
        });
    }
}
