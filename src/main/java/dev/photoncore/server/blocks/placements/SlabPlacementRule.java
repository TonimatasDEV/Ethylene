package dev.photoncore.server.blocks.placements;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlabPlacementRule extends BlockPlacementRule { // TODO: Waterlogged
    public SlabPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        Block newBlock = placementState.block();
        double blockCursorY = placementState.cursorPosition() != null ? placementState.cursorPosition().y() : 0;
        
        if (blockCursorY == 0.5) {
            return null;
        }

        if ((blockCursorY >= 0.5D && blockCursorY != 1.0) || blockCursorY == 0.0) {
            newBlock = newBlock.withProperty("type", "top");
        } else {
            newBlock = newBlock.withProperty("type", "bottom");
        }
        
        return newBlock;
    }
}
