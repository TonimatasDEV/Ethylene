package net.ethylene.server.blocks.placements;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class HorizontalPlacementRule extends BlockPlacementRule {
    public HorizontalPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        float yaw = placementState.playerPosition() != null ? placementState.playerPosition().yaw() : 136;
        BlockFace blockFace = BlockFace.fromYaw(yaw).getOppositeFace();
        return placementState.block().withProperty("facing", blockFace.name().toLowerCase(Locale.ENGLISH));
    }
}
