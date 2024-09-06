package dev.photoncore.server.blocks.handlers;

import dev.photoncore.server.utils.StringUtils;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class GateHandler implements BlockHandler {
    protected NamespaceID id;

    public GateHandler(NamespaceID id) {
        this.id = id;
    }

    @Override
    public boolean onInteract(@NotNull Interaction interaction) {
        if (interaction.getPlayer().isSneaking() && interaction.getPlayer().getItemInHand(interaction.getHand()).material().isBlock()) {
            return true;
        }
        
        float yaw = interaction.getPlayer().getPosition().yaw();
        String facing = BlockFace.fromYaw(yaw).toString().toLowerCase(Locale.ENGLISH);
        String open = interaction.getBlock().getProperty("open");
        
        open = StringUtils.inverseBoolean(open);
        
        interaction.getInstance().setBlock(interaction.getBlockPosition(), interaction.getBlock().withProperty("open", open).withProperty("facing", facing), true);

        return false;
    }

    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return id;
    }
}
