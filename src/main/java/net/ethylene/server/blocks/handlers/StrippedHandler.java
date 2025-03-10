package net.ethylene.server.blocks.handlers;

import net.ethylene.server.tags.Tags;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

public class StrippedHandler implements BlockHandler {
    protected NamespaceID id;

    public StrippedHandler(NamespaceID id) {
        this.id = id;
    }

    @Override
    public boolean onInteract(@NotNull Interaction interaction) {
        Player player = interaction.getPlayer();
       
        if (Tags.AXES.contains(player.getItemInHand(interaction.getHand()))) {
            Block block = Block.fromNamespaceId(interaction.getBlock().namespace().toString().replaceAll(":", ":stripped_"));

            if (block == null) return false;

            interaction.getInstance().setBlock(interaction.getBlockPosition(), block, true);
        }

        return true;
    }

    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return id;
    }
}
