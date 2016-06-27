package net.hycrafthd.corelib.util;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Util methods for {@link Block}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class BlockUtil {

	/**
	 * Get block from object
	 * 
	 * @param obj
	 *            Object
	 * @return Block or null if not found
	 */
	public static Block from(Object obj) {
		ItemStack stack = ItemStackUtil.from(obj);
		if (stack == null || stack.getItem() == null) {
			return null;
		}
		return Block.getBlockFromItem(stack.getItem());
	}

	/**
	 * Gets the tileentity at block position
	 * 
	 * @param world
	 *            Tileentity world
	 * @param pos
	 *            Tileentity position
	 * @return Tileentity or null if not found
	 */
	public static TileEntity getTileEntity(World world, BlockPos pos) {
		return world.getTileEntity(pos);
	}

}
