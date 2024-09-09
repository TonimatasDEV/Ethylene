package dev.polariscore.server.utils;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;

import java.util.Locale;

public class Utils {
    public static BlockFace getBlockFace(Block block) {
        return BlockFace.valueOf(block.getProperty("facing").toUpperCase(Locale.ENGLISH));
    }

    public static String getAxis(BlockFace value) {
        return switch (value) {
            case NORTH, SOUTH -> "z";
            case EAST, WEST -> "x";
            case TOP, BOTTOM -> "y";
        };
    }

    public static BlockFace getCounterClockWise(BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.WEST;
            case SOUTH -> BlockFace.EAST;
            case WEST -> BlockFace.SOUTH;
            case EAST -> BlockFace.NORTH;
            default -> throw new IllegalStateException("Unable to get CCW facing of " + blockFace);
        };
    }

    public static String inverseStrBoolean(String value) {
        if (value.equals("true")) {
            return "false";
        } else {
            return "true";
        }
    }
}
