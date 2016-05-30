package net.hycrafthd.corelib.util.cschematic;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;

public class BlockObjectOld {

	private Point3i pos;
	private IBlockState state;
	private NBTTagCompound tileentity;

	public BlockObjectOld(Point3i pos, IBlockState state, NBTTagCompound tileentity) {
		this.pos = pos;
		this.state = state;
		this.tileentity = tileentity;
	}

	public Point3i getPos() {
		return pos;
	}

	public IBlockState getState() {
		return state;
	}

	public NBTTagCompound getTileentity() {
		return tileentity;
	}

	@Override
	public String toString() {
		return pos + " -> " + state;
	}

}
