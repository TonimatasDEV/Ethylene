package net.ethylene.server.tags;

import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.registry.RegistryTag;
import net.minestom.server.registry.TagKey;

import java.util.Locale;

public enum Tags {
    WOODEN_FENCES,
    FENCE_GATES,
    AXES,
    WALLS,
    SLABS,
    STAIRS;
    
    private String getHashTag() {
        return "#minecraft:" + this.name().toLowerCase(Locale.ENGLISH);
    }
    
    public RegistryTag<Block> getBlockTag() {
        return Block.staticRegistry().getTag(TagKey.ofHash(getHashTag()));
    }
    
    public RegistryTag<Material> getItemTag() {
        return Material.staticRegistry().getTag(TagKey.ofHash(getHashTag()));
        
    }
    
    public boolean contains(Block block) {
        return getBlockTag().contains(block);
    }
    
    public boolean contains(ItemStack itemStack) {
        return getItemTag().contains(itemStack.material());
    }
}
