package net.hycrafthd.corelib.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registry for {@link Block}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class BlockRegistry {

	/**
	 * Register a new {@link Block}
	 * 
	 * @param block
	 *            Block instance
	 * @param name
	 *            Block name
	 */
	public static void register(Block block, String name) {
		register(block, ItemBlock.class, name);
	}

	/**
	 * Register a new {@link Block}
	 * 
	 * @param block
	 *            Block instance
	 * @param itemblock
	 *            Itemblock class
	 * @param name
	 *            Block name
	 */
	public static void register(Block block, Class<? extends ItemBlock> itemblock, String name) {
		GameRegistry.registerBlock(block, itemblock, name);
	}

}
