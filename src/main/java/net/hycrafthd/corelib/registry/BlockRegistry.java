package net.hycrafthd.corelib.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registry for {@link Block}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class BlockRegistry {

	/**
	 * Register a new block
	 * 
	 * @param block
	 *            Block instance
	 * @param name
	 *            Block name
	 */
	public static void register(Block block, String name) {
		register(block, Item.getItemFromBlock(block), name);
	}

	/**
	 * Register a new block
	 * 
	 * @param block
	 *            Block instance
	 * @param itemblock
	 *            Itemblock class
	 * @param name
	 *            Block name
	 */
	public static void register(Block block, Item itemblock, String name) {
		block.setRegistryName(name);
		block.setUnlocalizedName(name);
		GameRegistry.register(block);
		GameRegistry.register(itemblock);
	}

}
