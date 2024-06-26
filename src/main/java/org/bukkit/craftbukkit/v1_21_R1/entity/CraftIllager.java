package org.bukkit.craftbukkit.v1_21_R1.entity;

import net.minecraft.world.entity.monster.AbstractIllager;
import org.bukkit.craftbukkit.v1_21_R1.CraftServer;
import org.bukkit.entity.Illager;

public class CraftIllager extends CraftRaider implements Illager {

    public CraftIllager(CraftServer server, AbstractIllager entity) {
        super(server, entity);
    }

    @Override
    public AbstractIllager getHandle() {
        return (AbstractIllager) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftIllager";
    }
}
