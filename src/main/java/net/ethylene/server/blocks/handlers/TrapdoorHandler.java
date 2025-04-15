package net.ethylene.server.blocks.handlers;

import net.ethylene.server.utils.Utils;
import net.kyori.adventure.key.Key;
import net.minestom.server.instance.block.BlockHandler;
import org.jetbrains.annotations.NotNull;

public class TrapdoorHandler implements BlockHandler {
    protected Key key;

    public TrapdoorHandler(Key key) {
        this.key = key;
    }

    @Override
    public boolean onInteract(@NotNull Interaction interaction) {
        if (interaction.getPlayer().isSneaking() && interaction.getPlayer().getItemInHand(interaction.getHand()).material().isBlock()) {
            return true;
        }

        String open = interaction.getBlock().getProperty("open");

        open = Utils.inverseStrBoolean(open);

        interaction.getInstance().setBlock(interaction.getBlockPosition(), interaction.getBlock().withProperty("open", open), true);

        return false;
    }

    @Override
    public @NotNull Key getKey() {
        return key;
    }
}
