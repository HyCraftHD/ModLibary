package net.hycrafthd.corelib.util.cschematic;

import org.apache.commons.lang3.Validate;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class BlockObj {
	
	private Block block;
	private int meta;
	private NBTTagCompound ent;
	
	/**
	 * Class to Save a block for schematic
	 * 
	 * @param Block
	 *            block - Block to Save
	 * @param int
	 *            meta - meta of block
	 * @param NBTTagCompound
	 *            ent - NBT of TileEntity (writeToNBT)
	 */
	
	public BlockObj(Block block, int meta, NBTTagCompound ent) {
		this.block = block;
		this.meta = meta;
		this.ent = ent;
	}
	
	public Block getBlock() {
		return block;
	}
	
	/**
	 * @param block
	 */
	public void setBlock(Block block) {
		this.block = block;
	}
	
	/**
	 * @return meta
	 */
	public int getMeta() {
		return meta;
	}
	
	/**
	 * @param meta
	 */
	public void setMeta(int meta) {
		this.meta = meta;
	}
	
	/**
	 * @return NBTTag of TileEntity
	 */
	public NBTTagCompound getTileEntity() {
		return ent;
	}
	
	/**
	 * @param NBTTag
	 *            of Tile
	 */
	public void setTileEntity(NBTTagCompound ent) {
		this.ent = ent;
	}
	
	/**
	 * @return has this NBT
	 */
	public boolean hasNBT() {
		return ent != null;
	}
	
	/**
	 * @return this transformed in a NBTTag
	 */
	public NBTTagCompound toNBT() {
		NBTTagCompound comp = new NBTTagCompound();
		String block = String.valueOf(Block.REGISTRY.getNameForObject(this.getBlock()));
		comp.setString("Name", block);
		comp.setInteger("Meta", this.getMeta());
		if (this.hasNBT())
			comp.setTag("NBT", this.getTileEntity());
		return comp;
	}
	
	/**
	 * @param NBTTag
	 * @return BlockObj of NBTTag
	 */
	public static BlockObj fromNBT(NBTTagCompound comp) {
		return new BlockObj(Validate.notNull((Block) Block.REGISTRY.getObject(new ResourceLocation(comp.getString("Name")))), comp.getInteger("Meta"), (NBTTagCompound) comp.getTag("NBT"));
	}
}
