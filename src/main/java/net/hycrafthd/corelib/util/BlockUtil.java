package net.hycrafthd.corelib.util;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockUtil {

	public static Block from(Object obj) {
		ItemStack stack = ItemStackUtil.from(obj);
		if (stack == null || stack.getItem() == null) {
			return null;
		}
		return Block.getBlockFromItem(stack.getItem());
	}

	public static TileEntity getTileEntity(World world, BlockPos pos) {
		return world.getTileEntity(pos);
	}

}
