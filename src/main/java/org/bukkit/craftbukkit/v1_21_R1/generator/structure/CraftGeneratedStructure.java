package org.bukkit.craftbukkit.v1_21_R1.generator.structure;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.bukkit.generator.structure.GeneratedStructure;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructurePiece;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.util.BoundingBox;

import java.util.Collection;
import java.util.List;

public class CraftGeneratedStructure implements GeneratedStructure {

    private final StructureStart handle;
    private List<StructurePiece> pieces;

    public CraftGeneratedStructure(StructureStart handle) {
        this.handle = handle;
    }

    @Override
    public BoundingBox getBoundingBox() {
        net.minecraft.world.level.levelgen.structure.BoundingBox bb = handle.getBoundingBox();
        return new BoundingBox(bb.minX(), bb.minY(), bb.minZ(), bb.maxX(), bb.maxY(), bb.maxZ());
    }

    @Override
    public Structure getStructure() {
        return CraftStructure.minecraftToBukkit(handle.getStructure());
    }

    @Override
    public Collection<StructurePiece> getPieces() {
        if (pieces == null) { // Cache the pieces on first request
            ImmutableList.Builder<StructurePiece> builder = new ImmutableList.Builder<>();
            for (net.minecraft.world.level.levelgen.structure.StructurePiece piece : handle.getPieces()) {
                builder.add(new CraftStructurePiece(piece));
            }

            pieces = builder.build();
        }

        return this.pieces;
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return handle.persistentDataContainer;
    }
}
