package net.ethylene.server.tags;

import net.minestom.server.MinecraftServer;
import net.minestom.server.gamedata.tags.Tag;

import java.util.Objects;

public enum Tags {
    WOODEN_FENCES("wooden_fences", Tag.BasicType.BLOCKS),
    FENCE_GATES("fence_gates", Tag.BasicType.BLOCKS);
    
    private final String tag;
    private final Tag.BasicType type;
    Tags(String tag, Tag.BasicType type) {
        this.tag = tag;
        this.type = type;
    }
    
    public Tag get() {
        return Objects.requireNonNull(MinecraftServer.getTagManager().getTag(type, "minecraft:" + tag));
    }
}
