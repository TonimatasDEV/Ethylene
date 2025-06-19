package net.ethylene.server.blocks.handlers;

import net.ethylene.server.utils.Utils;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.inventory.type.FurnaceInventory;
import org.jetbrains.annotations.NotNull;

public class SmokerHandler implements BlockHandler {
    @Override
    public boolean onInteract(@NotNull BlockHandler.Interaction interaction) {
        if (interaction.getPlayer().isSneaking() && Utils.hasItemInHands(interaction.getPlayer())) {
            return true;
        }
        
        interaction.getPlayer().openInventory(new FurnaceInventory(Component.translatable("container.smoker")));
        return false;
    }

    @Override
    public @NotNull Key getKey() {
        return Block.SMOKER.key();
    }
}
