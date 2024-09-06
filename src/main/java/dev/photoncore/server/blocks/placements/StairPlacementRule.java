package dev.photoncore.server.blocks.placements;

import dev.photoncore.server.utils.Utils;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.gamedata.tags.Tag;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class StairPlacementRule extends BlockPlacementRule {
    public StairPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        return updateState.currentBlock().withProperty("shape", getShape(updateState.instance(), updateState.currentBlock(), updateState.blockPosition()));
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        float yaw = placementState.playerPosition() != null ? placementState.playerPosition().yaw() : 136;
        BlockFace blockFace = BlockFace.fromYaw(yaw);
        
        Block block = placementState.block().withProperty("facing", blockFace.name().toLowerCase(Locale.ENGLISH));

        double blockCursorY = placementState.cursorPosition() != null ? placementState.cursorPosition().y() : 0;
        

        if ((blockCursorY >= 0.5D && blockCursorY != 1.0) || blockCursorY == 0.0) {
            block = block.withProperty("half", "top");
        } else {
            block = block.withProperty("half", "bottom");
        }
        
        return block.withProperty("shape", getShape(placementState.instance(), block, placementState.placePosition()));
    }

    private static String getShape(Block.Getter instance, Block block, Point point) {
        BlockFace face = Utils.getBlockFace(block);
        Block relativeBlock = instance.getBlock(point.relative(face));
        
        if (isStair(relativeBlock) && block.getProperty("half").equals(relativeBlock.getProperty("half"))) {
            BlockFace relativeFace = Utils.getBlockFace(relativeBlock);
            
            if (!Utils.getAxis(relativeFace).equals(Utils.getAxis(face)) && canTakeShape(block, instance, point, relativeFace.getOppositeFace())) {
                if (relativeFace == Utils.getCounterClockWise(face)) {
                    return "outer_left";
                }
                
                return "outer_right";
            }
        }
        
        Block relativeBlock1 =  instance.getBlock(point.relative(face.getOppositeFace()));
        if (isStair(relativeBlock1) && block.getProperty("half").equals(relativeBlock1.getProperty("half"))) {
            BlockFace relativeFace = Utils.getBlockFace(relativeBlock1);

            if (!Utils.getAxis(relativeFace).equals(Utils.getAxis(face)) && canTakeShape(block, instance, point, relativeFace)) {
                if (relativeFace == Utils.getCounterClockWise(face)) {
                    return "inner_left";
                }
                
                return "inner_right";
            }
        }
        
        return "straight";
    }
    
    private static boolean canTakeShape(Block block, Block.Getter instance, Point point, BlockFace face) {
        Block relativeBlock = instance.getBlock(point.relative(face));
        return !isStair(relativeBlock) || !block.getProperty("facing").equals(relativeBlock.getProperty("facing")) 
                || !block.getProperty("half").equals(relativeBlock.getProperty("half"));
    }
    
    private static boolean isStair(Block block) {
        Tag tag = MinecraftServer.getTagManager().getTag(Tag.BasicType.BLOCKS, "minecraft:stairs");
        assert tag != null;
        return tag.contains(block.namespace());
    }
}
