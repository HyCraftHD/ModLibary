package net.hycrafthd.corelib.util.cschematic;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

public class BlockObj {

	private Block block;
	private int meta;
	private NBTTagCompound ent;
	
	public BlockObj(Block block,int meta,NBTTagCompound ent) {
		this.block = block;
		this.meta = meta;
		this.ent = ent;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public int getMeta() {
		return meta;
	}

	public void setMeta(int meta) {
		this.meta = meta;
	}

	public NBTTagCompound getTileEntity() {
		return ent;
	}

	public void setTileEntity(NBTTagCompound ent) {
		this.ent = ent;
	}
	
	public boolean hasNBT(){
		return ent != null;
	}
	
	public NBTTagCompound toNBT(){
		NBTTagCompound comp = new NBTTagCompound();
		String block = String.valueOf(Block.blockRegistry.getNameForObject(this.getBlock()));
		comp.setString("Name", block);
		comp.setInteger("Meta", this.getMeta());
		if(this.hasNBT())
		comp.setTag("NBT", this.getTileEntity());
		return comp;
	}
	
	public static BlockObj fromNBT(NBTTagCompound comp){
		return new BlockObj((Block)Block.blockRegistry.getObject(comp.getString("Name")), comp.getInteger("Meta"), (NBTTagCompound) comp.getTag("NBT"));
	}
}
