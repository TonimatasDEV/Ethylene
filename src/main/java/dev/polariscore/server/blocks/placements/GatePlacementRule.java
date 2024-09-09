package dev.polariscore.server.blocks.placements;

import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GatePlacementRule extends HorizontalPlacementRule { // TODO: Waterlogged
    public GatePlacementRule(@NotNull Block block) {
        super(block);
    }

    private static String inWall(Block block) { // TODO: I need to do walls first
        String direction = block.getProperty("facing");

        if (direction.equals("north") || direction.equals("south")) {

        } else {

        }

        return "false";
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        Block block = super.blockPlace(placementState);

        if (block == null) return null;

        block = block.withProperty("in_wall", inWall(placementState.block()));
        return block;
    }
}
