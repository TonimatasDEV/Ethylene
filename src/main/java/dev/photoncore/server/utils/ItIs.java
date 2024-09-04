package dev.photoncore.server.utils;

import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ItIs {
    public static boolean anAxe(ItemStack itemStack) {
        return itemStack.isSimilar(ItemStack.of(Material.WOODEN_AXE)) || 
                itemStack.isSimilar(ItemStack.of(Material.STONE_AXE)) || 
                itemStack.isSimilar(ItemStack.of(Material.IRON_AXE)) || 
                itemStack.isSimilar(ItemStack.of(Material.GOLDEN_AXE)) || 
                itemStack.isSimilar(ItemStack.of(Material.DIAMOND_AXE)) || 
                itemStack.isSimilar(ItemStack.of(Material.NETHERITE_AXE));
    }
}
