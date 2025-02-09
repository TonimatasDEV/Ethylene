package net.ethylene.server.blocks.handlers;

import net.ethylene.server.utils.Utils;
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
        String facing = interaction.getBlock().getProperty("facing");
        String open = interaction.getBlock().getProperty("open");
        String playerFacing = BlockFace.fromYaw(yaw).toString().toLowerCase(Locale.ENGLISH);


        if (facing.equals("south") || facing.equals("north")) {
            if (playerFacing.equals("north")) {
                facing = "north";
            } else {
                facing = "south";
            }
        } else {
            if (playerFacing.equals("east")) {
                facing = "east";
            } else {
                facing = "west";
            }
        }

        open = Utils.inverseStrBoolean(open);

        interaction.getInstance().setBlock(interaction.getBlockPosition(), interaction.getBlock().withProperty("open", open).withProperty("facing", facing), true);

        return false;
    }

    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return id;
    }
}
