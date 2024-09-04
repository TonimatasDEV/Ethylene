package dev.photoncore.server.blocks.placements;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ConnectedPlacementRule extends BlockPlacementRule {
    public ConnectedPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public int maxUpdateDistance() {
        return 1;
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        return updateState.currentBlock().withProperties(getProperties(updateState.instance(), updateState.blockPosition()));
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        
        
        return placementState.block().withProperties(getProperties(placementState.instance(), placementState.placePosition()));
    }
    
    public static Map<String, String> getProperties(Block.Getter instance, Point point) {
        Map<String, String> properties = new HashMap<>();

        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.TOP || face == BlockFace.BOTTOM) continue;

            Block faceBlock = instance.getBlock(point.relative(face));

            if (faceBlock.isSolid()) { // TODO Add all ignored blocks. Like Lantern, closed trapdoor, chain....
                properties.put(face.name().toLowerCase(Locale.ENGLISH), "true");
            } else {
                properties.put(face.name().toLowerCase(Locale.ENGLISH), "false");
            }
        }
        
        return properties;
    }
}
