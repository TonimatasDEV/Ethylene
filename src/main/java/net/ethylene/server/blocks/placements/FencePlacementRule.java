package net.ethylene.server.blocks.placements;

import net.ethylene.server.tags.Tags;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FencePlacementRule extends BlockPlacementRule { // TODO: Waterlogged
    public FencePlacementRule(@NotNull Block block) {
        super(block);
    }

    private static Map<String, String> getProperties(Block block, Block.Getter instance, Point point) {
        Map<String, String> properties = new HashMap<>();

        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.TOP || face == BlockFace.BOTTOM) continue;

            Block faceBlock = instance.getBlock(point.relative(face));

            if (faceBlock.isAir()) {
                properties.put(face.name().toLowerCase(Locale.ENGLISH), "false");
                continue;
            }

            if (faceBlock.registry().collisionShape().isFaceFull(face.getOppositeFace())) {
                properties.put(face.name().toLowerCase(Locale.ENGLISH), "true");
                continue;
            }

            Block blockWithDirection = block.withProperty(face.name().toLowerCase(Locale.ENGLISH), "true");

            if (faceBlock.registry().collisionShape().isOccluded(blockWithDirection.registry().collisionShape(), face)
                    || Tags.WOODEN_FENCES.get().contains(faceBlock.namespace())) {
                properties.put(face.name().toLowerCase(Locale.ENGLISH), "true");
            } else {
                properties.put(face.name().toLowerCase(Locale.ENGLISH), "false");
            }

            if (Tags.FENCE_GATES.get().contains(faceBlock.namespace())) {
                BlockFace gateFace = BlockFace.valueOf(faceBlock.getProperty("facing").toUpperCase(Locale.ENGLISH));

                boolean connect = switch (face) {
                    case NORTH, SOUTH -> !(gateFace == BlockFace.NORTH || gateFace == BlockFace.SOUTH);
                    case WEST, EAST -> !(gateFace == BlockFace.WEST || gateFace == BlockFace.EAST);
                    default -> false;
                };

                properties.put(face.name().toLowerCase(Locale.ENGLISH), String.valueOf(connect));
            }

            if (block.namespace().equals(Block.NETHER_BRICK_FENCE.namespace())) {
                if (Tags.WOODEN_FENCES.get().contains(faceBlock.namespace())) {
                    properties.put(face.name().toLowerCase(Locale.ENGLISH), "false");
                } else if (faceBlock.namespace().equals(Block.NETHER_BRICK_FENCE.namespace())) {
                    properties.put(face.name().toLowerCase(Locale.ENGLISH), "true");
                }
            }
        }

        return properties;
    }

    @Override
    public int maxUpdateDistance() {
        return 1;
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        return updateState.currentBlock().withProperties(getProperties(updateState.currentBlock(), updateState.instance(), updateState.blockPosition()));
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        Block block = placementState.block();
        return block.withProperties(getProperties(placementState.block(), placementState.instance(), placementState.placePosition()));
    }
}
