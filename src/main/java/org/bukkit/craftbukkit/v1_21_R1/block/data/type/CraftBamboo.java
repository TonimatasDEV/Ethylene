package org.bukkit.craftbukkit.v1_21_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.craftbukkit.v1_21_R1.block.data.CraftBlockData;

public abstract class CraftBamboo extends CraftBlockData implements Bamboo {

    private static final EnumProperty<?> LEAVES = getEnum("leaves");

    @Override
    public org.bukkit.block.data.type.Bamboo.Leaves getLeaves() {
        return get(LEAVES, org.bukkit.block.data.type.Bamboo.Leaves.class);
    }

    @Override
    public void setLeaves(org.bukkit.block.data.type.Bamboo.Leaves leaves) {
        set(LEAVES, leaves);
    }
}
