package dev.polariscore.server.blocks.handlers;

import net.kyori.adventure.text.Component;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.inventory.type.FurnaceInventory;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

public class FurnaceHandler implements BlockHandler {
    @Override
    public boolean onInteract(@NotNull BlockHandler.Interaction interaction) {
        interaction.getPlayer().openInventory(new FurnaceInventory(Component.translatable("container.furnace")));
        return false;
    }
    
    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return Block.FURNACE.namespace();
    }
}
