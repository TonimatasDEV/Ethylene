package dev.photoncore.server.blocks.placements;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FacePlacementRule extends BlockPlacementRule {
    public FacePlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @Nullable Block blockPlace(@NotNull BlockPlacementRule.PlacementState placementState) {
        BlockFace blockFace = placementState.blockFace();
        
        String axis = "y";
        
        if (blockFace == BlockFace.NORTH || blockFace == BlockFace.SOUTH) {
            axis = "z";
        }

        if (blockFace == BlockFace.WEST || blockFace == BlockFace.EAST) {
            axis = "x";
        }
        
        return placementState.block().withProperty("axis", axis);
    }
}
