package net.hycrafthd.corelib.util.cschematic;

import javafx.geometry.Pos;
import net.hycrafthd.corelib.util.MathUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class SchematicBoundingBox extends StructureBoundingBox {

	public SchematicBoundingBox(BlockPos pos1, BlockPos pos2) {
		super(pos1, pos2);
	}

	public BlockPos getMinPos() {
		return new BlockPos(minX, minY, minZ);
	}

	public BlockPos getMaxPos() {
		return new BlockPos(maxX, maxY, maxZ);
	}

}
