package net.hycrafthd.corelib.util.cschematic;

import java.util.ArrayList;

import net.hycrafthd.corelib.util.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class Schematic {

	private BlockPos pos1,pos2;
	private World worldObj;
	public final int disx;
	public final int disy;
	public final int disz;
	
	public Schematic(BlockPos pos1,BlockPos pos2,World w) {
		this.pos1 = new BlockPos(MathUtil.getMinVec(pos1, pos2));
		this.pos2 = new BlockPos(MathUtil.getMaxVec(pos1, pos2));
		this.worldObj = w;
		this.disx = MathUtil.distance(this.pos1.getX(), this.pos2.getX() + 1);
		this.disy = MathUtil.distance(this.pos1.getY(), this.pos2.getY() + 1);
		this.disz = MathUtil.distance(this.pos1.getZ(), this.pos2.getZ() + 1);
	}

	public BlockPos getPos1() {
		return pos1;
	}

	public void setPos1(BlockPos pos1) {
		this.pos1 = pos1;
	}

	public BlockPos getPos2() {
		return pos2;
	}

	public void setPos2(BlockPos pos2) {
		this.pos2 = pos2;
	}
	
	public BlockObj[] getBlocks(){
		ArrayList<BlockObj> args = new ArrayList<BlockObj>();
		StructureBoundingBox bon = new StructureBoundingBox(pos1, pos2);
		BlockPos posstart = new BlockPos(MathUtil.getMinVec(pos1, pos2));
		if(worldObj.isAreaLoaded(bon)){
			for (int z = 0; z < disz; z++) {
				for (int y = 0; y < disy; y++) {
					for (int x = 0; x < disx; x++) {
						BlockPos pos = posstart.add(new Vec3i(x, y, z));
						IBlockState stat = worldObj.getBlockState(pos);
						Block bl = stat.getBlock();
						int meta = bl.getMetaFromState(stat);
						NBTTagCompound tag = null;
						if(bl.hasTileEntity(stat)){
							tag = new NBTTagCompound();
							worldObj.getTileEntity(pos).writeToNBT(tag);
						}
						args.add(new BlockObj(bl, meta, tag));
					}
				}			
			}
		}
		BlockObj[] arr = new BlockObj[args.size()];
		int i = 0;
		for (BlockObj blockObj : args) {
			arr[i] = blockObj;
			i++;
		}
		return arr;
	}

	public World getWorld() {
		return worldObj;
	}

	public void setWorld(World worldObj) {
		this.worldObj = worldObj;
	}
}
