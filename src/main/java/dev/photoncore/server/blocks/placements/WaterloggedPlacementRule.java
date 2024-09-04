package dev.photoncore.server.blocks.placements;

import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class WaterloggedPlacementRule extends ConnectedPlacementRule {
    public WaterloggedPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        // TODO: Do waterlogged logic
        
        return super.blockPlace(placementState);
    }
}
