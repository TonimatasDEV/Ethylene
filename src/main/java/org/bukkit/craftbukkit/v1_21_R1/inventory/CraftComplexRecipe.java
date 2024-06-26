package org.bukkit.craftbukkit.v1_21_R1.inventory;

import dev.tonimatas.ethylene.StaticMethods;
import dev.tonimatas.ethylene.link.world.item.crafting.RecipeManagerLink;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_21_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.inventory.ItemStack;

public class CraftComplexRecipe implements CraftRecipe, ComplexRecipe {

    private final NamespacedKey key;
    private final CustomRecipe recipe;

    public CraftComplexRecipe(NamespacedKey key, CustomRecipe recipe) {
        this.key = key;
        this.recipe = recipe;
    }

    @Override
    public ItemStack getResult() {
        return CraftItemStack.asCraftMirror(recipe.getResultItem(RegistryAccess.EMPTY));
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public void addToCraftingManager() {
        ((RecipeManagerLink) StaticMethods.getServer().getRecipeManager()).addRecipe(new RecipeHolder<>(CraftNamespacedKey.toMinecraft(key), recipe)); // Ethylene
    }
}
