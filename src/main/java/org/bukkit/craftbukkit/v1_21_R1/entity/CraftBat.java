package org.bukkit.craftbukkit.v1_21_R1.entity;

import org.bukkit.craftbukkit.v1_21_R1.CraftServer;
import org.bukkit.entity.Bat;

public class CraftBat extends CraftAmbient implements Bat {
    public CraftBat(CraftServer server, net.minecraft.world.entity.ambient.Bat entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.ambient.Bat getHandle() {
        return (net.minecraft.world.entity.ambient.Bat) entity;
    }

    @Override
    public String toString() {
        return "CraftBat";
    }

    @Override
    public boolean isAwake() {
        return !getHandle().isResting();
    }

    @Override
    public void setAwake(boolean state) {
        getHandle().setResting(!state);
    }
}
