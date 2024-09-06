package dev.photoncore.server.blocks.handlers;

import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

public class TrapdoorHandler implements BlockHandler {
    protected NamespaceID id;

    public TrapdoorHandler(NamespaceID id) {
        this.id = id;
    }
    
    @Override
    public boolean onInteract(@NotNull Interaction interaction) {
        if (interaction.getPlayer().isSneaking() && interaction.getPlayer().getItemInHand(interaction.getHand()).material().isBlock()) {
            return true;
        }
        
        String open = interaction.getBlock().getProperty("open");
        
        if (open.equals("true")) {
            open = "false";
        } else {
            open = "true";
        }
        
        interaction.getInstance().setBlock(interaction.getBlockPosition(), interaction.getBlock().withProperty("open", open), true);
        
        return false;
    }

    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return id;
    }
}
