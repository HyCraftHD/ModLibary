package net.hycrafthd.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class CommonRegistry {

	/**
	 * Register a new item
	 * 
	 * @param item Item instance
	 * @param itemname Item name
	 */
	public static void registerItem(Item item, String itemname) {

		if (!CoreUtil.isUnsupportedVersion()) {
			try {
				if (CoreUtil.contains1_8()) {

					Method unlocalizedname = item.getClass().getMethod("setUnlocalizedName", String.class);
					Method register = CoreUtil.gameregistryclass.getMethod("registerItem", Item.class, String.class);

					unlocalizedname.invoke(item, itemname);
					register.invoke(null, item, itemname);

				} else if (CoreUtil.contains1_9()) {

					Class iforgeregistry = Class.forName("net.minecraftforge.fml.common.registry.IForgeRegistryEntry");

					Method unlocalizedname = item.getClass().getMethod("setUnlocalizedName", String.class);
					Method registryname = item.getClass().getMethod("setRegistryName", String.class);
					Method register = CoreUtil.gameregistryclass.getMethod("register", iforgeregistry);

					unlocalizedname.invoke(item, itemname);
					registryname.invoke(item, itemname);
					register.invoke(iforgeregistry, item);

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Register a new block with default ItemBlock
	 * 
	 * @param block Block instance
	 * @param blockname Block name
	 */
	public static void registerBlock(Block block, String blockname) {
		registerBlock(block, ItemBlock.class, blockname);
	}

	/**
	 * Register a new block
	 * 
	 * @param block Block instance
	 * @param itemblock ItemBlock class
	 * @param blockname Block name
	 */
	public static void registerBlock(Block block, Class<? extends ItemBlock> itemblock, String blockname) {

		if (!CoreUtil.isUnsupportedVersion()) {
			try {
				if (CoreUtil.contains1_8()) {

					Method unlocalizedname = block.getClass().getMethod("setUnlocalizedName", String.class);
					Method register = CoreUtil.gameregistryclass.getMethod("registerBlock", Block.class, Class.class, String.class);

					unlocalizedname.invoke(block, blockname);
					register.invoke(null, block, itemblock, blockname);

				} else if (CoreUtil.contains1_9()) {

					Class iforgeregistry = Class.forName("net.minecraftforge.fml.common.registry.IForgeRegistryEntry");

					Constructor<? extends ItemBlock> constructor = itemblock.getConstructor(Block.class);
					ItemBlock itemblockinstance = constructor.newInstance(block);

					Method unlocalizedname = block.getClass().getMethod("setUnlocalizedName", String.class);
					Method registryname = block.getClass().getMethod("setRegistryName", String.class);
					Method unlocalizedname2 = itemblockinstance.getClass().getMethod("setUnlocalizedName", String.class);
					Method registryname2 = itemblockinstance.getClass().getMethod("setRegistryName", String.class);
					Method register = CoreUtil.gameregistryclass.getMethod("register", iforgeregistry);

					unlocalizedname.invoke(block, blockname);
					registryname.invoke(block, blockname);
					unlocalizedname2.invoke(itemblockinstance, blockname);
					registryname2.invoke(itemblockinstance, blockname);
					register.invoke(iforgeregistry, block);
					register.invoke(iforgeregistry, itemblockinstance);

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
