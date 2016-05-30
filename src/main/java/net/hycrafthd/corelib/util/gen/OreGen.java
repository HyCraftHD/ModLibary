package net.hycrafthd.corelib.util.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class OreGen {

	private IBlockState state;
	private Block blockin;
	private int chance;
	private int minY;
	private int maxY;
	private int minVienSize;
	private int maxVienSize;

	public OreGen(IBlockState state, Block blockin, int chance, int minY, int maxY, int minVienSize, int maxVienSize) {
		this.state = state;
		this.blockin = blockin;
		this.chance = chance;
		this.minY = minY;
		this.maxY = maxY;
		this.minVienSize = minVienSize;
		this.maxVienSize = maxVienSize;
	}

	public Block getBlockin() {
		return blockin;
	}

	public int getChance() {
		return chance;
	}

	public int getMaxVienSize() {
		return maxVienSize;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMinVienSize() {
		return minVienSize;
	}

	public int getMinY() {
		return minY;
	}

	public IBlockState getState() {
		return state;
	}

}
