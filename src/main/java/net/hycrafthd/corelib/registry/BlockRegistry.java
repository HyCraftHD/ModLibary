package net.hycrafthd.corelib.registry;

import java.lang.reflect.Constructor;

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
	 * Register a new block
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
	 * Register a new block
	 * 
	 * @param block
	 *            Block instance
	 * @param itemblock
	 *            Itemblock class
	 * @param name
	 *            Block name
	 */
	public static void register(Block block, Class<? extends ItemBlock> itemblockClass, String name) {
		block.setUnlocalizedName(name);
		block.setRegistryName(name);
		GameRegistry.register(block);
		
		ItemBlock itemblock = null;
		
		try {
			Constructor<?> cons = itemblockClass.getConstructor(Block.class);
			itemblock = (ItemBlock) cons.newInstance(block);
		} catch (Throwable th) {
			itemblock = new ItemBlock(block);
		}
		GameRegistry.register(itemblock.setUnlocalizedName(name).setRegistryName(name));
	}
	
}
