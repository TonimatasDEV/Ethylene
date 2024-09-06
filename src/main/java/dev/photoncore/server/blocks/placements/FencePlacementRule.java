package dev.photoncore.server.blocks.placements;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.gamedata.tags.Tag;
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

    @Override
    public int maxUpdateDistance() {
        return 1;
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        return updateState.currentBlock().withProperties(getProperties(updateState.currentBlock(),updateState.instance(), updateState.blockPosition()));
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        Block block = placementState.block();
        return block.withProperties(getProperties(placementState.block(), placementState.instance(), placementState.placePosition()));
    }
    
    public static Map<String, String> getProperties(Block block, Block.Getter instance, Point point) {
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
            Tag tag1 = MinecraftServer.getTagManager().getTag(Tag.BasicType.BLOCKS, "minecraft:wooden_fences");
            Tag tag2 = MinecraftServer.getTagManager().getTag(Tag.BasicType.BLOCKS, "minecraft:fence_gates");

            if (faceBlock.registry().collisionShape().isOccluded(blockWithDirection.registry().collisionShape(), face) ||
                    (tag1 != null && tag1.contains(faceBlock.namespace())) || (tag2 != null && tag2.contains(faceBlock.namespace()))) {
                properties.put(face.name().toLowerCase(Locale.ENGLISH), "true");
            } else {
                properties.put(face.name().toLowerCase(Locale.ENGLISH), "false");
            }
        }
        
        return properties;
    }
}
