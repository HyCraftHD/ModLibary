package net.hycrafthd.corelib.util.cschematic;

import net.hycrafthd.corelib.util.ItemStackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public class RelativeRegion {

	private Point3i pos;
	private BlockObjectOld blocks[];

	public RelativeRegion(Point3i pos, BlockObjectOld[] blocks) {
		this.pos = pos;
		this.blocks = blocks;
	}

	public static RelativeRegion load(NBTTagCompound tag) {
		int size = tag.getInteger("size");
		int width = tag.getInteger("width");
		int height = tag.getInteger("height");
		int length = tag.getInteger("length");
		NBTTagList list = tag.getTagList("blocks", NBT.TAG_COMPOUND);
		Point3i pos = new Point3i(width, height, length);
		BlockObjectOld[] blocks = new BlockObjectOld[size];
		int c = 0;
		for (int z = 0; z < length; z++) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					Point3i relpos = new Point3i(x, y, z);
					NBTTagCompound blocktag = list.getCompoundTagAt(c);
					Block block = (Block) Block.blockRegistry.getObject(blocktag.getString("id"));
					IBlockState state = block.getStateFromMeta(blocktag.getInteger("meta"));
					NBTTagCompound tileentity = null;
					if (blocktag.hasKey("tileentity")) {
						tileentity = blocktag.getCompoundTag("tileentity");
					}
					blocks[c] = new BlockObjectOld(relpos, state, tileentity);
					c++;
				}
			}
		}
		return new RelativeRegion(pos, blocks);

	}

	public NBTTagCompound save() {
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		for (BlockObjectOld blockobj : blocks) {
			NBTTagCompound blocktag = new NBTTagCompound();
			IBlockState state = blockobj.getState();
			Block block = state.getBlock();
			blocktag.setString("id", String.valueOf(Block.blockRegistry.getNameForObject(block)));
			blocktag.setInteger("meta", block.getMetaFromState(state));
			if (blockobj.getTileentity() != null) {
				NBTTagCompound tileentity = blockobj.getTileentity();
				tileentity.removeTag("x");
				tileentity.removeTag("y");
				tileentity.removeTag("z");
				blocktag.setTag("tileentity", tileentity);
			}
			list.appendTag(blocktag);
		}
		tag.setInteger("size", blocks.length);
		tag.setTag("blocks", list);
		tag.setInteger("width", pos.getX());
		tag.setInteger("height", pos.getY());
		tag.setInteger("length", pos.getZ());
		return tag;
	}

	public Point3i getPos() {
		return pos;
	}

	public BlockObjectOld[] getBlocks() {
		return blocks;
	}

}
