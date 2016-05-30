package net.hycrafthd.corelib.util.cschematic;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class BlockObject {

	private String blockstring;
	private int meta;

	public BlockObject(String blockstring, int meta) {
		this.blockstring = blockstring;
		this.meta = meta;
	}

	public BlockObject(Block block, int meta) {
		this(String.valueOf(Block.blockRegistry.getNameForObject(block)), meta);
	}

	public BlockObject(IBlockState state) {
		this(state.getBlock(), state.getBlock().getMetaFromState(state));
	}

	public String getBlockstring() {
		return blockstring;
	}

	public int getMeta() {
		return meta;
	}

	public Block getBlock() {
		return (Block) Block.blockRegistry.getObject(blockstring);
	}

	public IBlockState getState() {
		return getBlock().getStateFromMeta(meta);
	}

}
