package net.hycrafthd.corelib.util.cschematic;

import net.hycrafthd.corelib.util.MathUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class AbsoluteRegion {

	private World world;
	private BlockPos pos1;
	private BlockPos pos2;

	public AbsoluteRegion(World world, BlockPos pos1, BlockPos pos2) {
		this.world = world;
		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	public World getWorld() {
		return world;
	}

	public BlockPos getPos1() {
		return pos1;
	}

	public BlockPos getPos2() {
		return pos2;
	}

	public static AbsoluteRegion fromRelative(RelativeRegion relrg, World world, BlockPos posstart) {
		int disx = relrg.getPos().getX();
		int disy = relrg.getPos().getY();
		int disz = relrg.getPos().getZ();
		int size = disx * disy * disz;

		Point3i relposend = new Point3i(disx, disy, disz);
		BlockPos posend = posstart.add(relposend);

		if (world.isAreaLoaded(posstart, posend)) {
			BlockObjectOld[] blocks = relrg.getBlocks();
			int c = 0;
			for (int z = 0; z < disz; z++) {
				for (int y = 0; y < disy; y++) {
					for (int x = 0; x < disx; x++) {
						Point3i acrelpos = new Point3i(x, y, z);
						BlockPos accabpos = posstart.add(acrelpos);

						BlockObjectOld blockobj = blocks[c];

						world.setBlockState(accabpos, blockobj.getState());

						TileEntity t = world.getTileEntity(accabpos);

						if (t != null && blockobj.getTileentity() != null) {
							NBTTagCompound tileentity = blockobj.getTileentity();
							tileentity.setInteger("x", accabpos.getX());
							tileentity.setInteger("y", accabpos.getY());
							tileentity.setInteger("z", accabpos.getZ());
							t.readFromNBT(blockobj.getTileentity());
						}

						c++;
					}
				}
			}

			return new AbsoluteRegion(world, posstart, posend);

		} else {
			throw new AreaNotLoadedExeption(posstart, posend);
		}

	}

	public RelativeRegion toRelative() {
		int disx = MathUtil.distance(pos1.getX(), pos2.getX() + 1);
		int disy = MathUtil.distance(pos1.getY(), pos2.getY() + 1);
		int disz = MathUtil.distance(pos1.getZ(), pos2.getZ() + 1);

		int size = disx * disy * disz;

		BlockPos posstart = new BlockPos(MathUtil.getMinVec(pos1, pos2));

		Point3i rpos = new Point3i(disx, disy, disz);

		if (world.isAreaLoaded(new StructureBoundingBox(pos1, pos2))) {

			BlockObjectOld[] blocks = new BlockObjectOld[size];
			int c = 0;
			for (int z = 0; z < disz; z++) {
				for (int y = 0; y < disy; y++) {
					for (int x = 0; x < disx; x++) {
						Point3i acrelpos = new Point3i(x, y, z);
						BlockPos accabpos = posstart.add(acrelpos);

						NBTTagCompound tileentity = null;
						TileEntity t = world.getTileEntity(accabpos);

						if (t != null) {
							tileentity = new NBTTagCompound();
							t.writeToNBT(tileentity);
						}

						blocks[c] = new BlockObjectOld(acrelpos, world.getBlockState(posstart.add(acrelpos)), tileentity);

						c++;
					}
				}
			}
			return new RelativeRegion(rpos, blocks);

		} else {
			throw new AreaNotLoadedExeption(pos1, pos2);
		}
	}
}
