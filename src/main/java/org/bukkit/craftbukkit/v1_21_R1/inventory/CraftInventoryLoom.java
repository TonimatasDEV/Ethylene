package org.bukkit.craftbukkit.v1_21_R1.inventory;

import net.minecraft.world.Container;
import org.bukkit.inventory.LoomInventory;

public class CraftInventoryLoom extends CraftResultInventory implements LoomInventory {

    public CraftInventoryLoom(Container inventory, Container resultInventory) {
        super(inventory, resultInventory);
    }
}
