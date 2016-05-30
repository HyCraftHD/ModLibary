package net.hycrafthd.corelib.util.gen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class OreGenDim extends OreGen {

	private int dimid;

	public OreGenDim(int dimid, OreGen oregen) {
		this(dimid, oregen.getState(), oregen.getBlockin(), oregen.getChance(), oregen.getMinY(), oregen.getMaxY(), oregen.getMinVienSize(), oregen.getMaxVienSize());
	}

	public OreGenDim(int dimid, IBlockState state, Block blockin, int chance, int minY, int maxY, int minVienSize, int maxVienSize) {
		super(state, blockin, chance, minY, maxY, minVienSize, maxVienSize);
		this.dimid = dimid;
	}

	public int getDimid() {
		return dimid;
	}

	public OreGen getOreGen() {
		return new OreGen(getState(), getBlockin(), getChance(), getMinY(), getMaxY(), getMinVienSize(), getMaxVienSize());
	}

}
