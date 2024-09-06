package dev.photoncore.server.blocks.placements;

import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrapdoorPlacementRule extends HorizontalPlacementRule {
    public TrapdoorPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        Block block = super.blockPlace(placementState);
        double blockCursorY = placementState.cursorPosition() != null ? placementState.cursorPosition().y() : 0;
        
        if (block == null) return null;

        if ((blockCursorY >= 0.5D && blockCursorY != 1.0) || blockCursorY == 0.0) {
            block = block.withProperty("half", "top");
        } else {
            block = block.withProperty("half", "bottom");
        }
        
        return block;
    }
}
