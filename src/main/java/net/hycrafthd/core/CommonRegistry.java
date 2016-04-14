package net.hycrafthd.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.hycrafthd.core.tileentity.ICustomTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class CommonRegistry {

	/**
	 * Add a new shaped crafting recipe
	 * 
	 * @param output Itemstack instance
	 * @param obj Craftingfield object
	 */
	public static void addShapedRecipe(ItemStack output, Object... obj) {
		CoreUtil.gameregistry.addShapedRecipe(output, obj);
	}

	/**
	 * Add new shapeless crafting recipe
	 * 
	 * @param output Itemstack instance
	 * @param obj Item / Block / ItemStack input instance
	 */
	public static void addShapelessRecipe(ItemStack output, Object... obj) {
		CoreUtil.gameregistry.addShapelessRecipe(output, obj);
	}

	/**
	 * Add new smelting recipe
	 * 
	 * @param input Item / Block / ItemStack instance
	 * @param output ItemStack instance
	 * @param xp XP per item
	 */
	public static void addSmelting(Object input, ItemStack output, float xp) {
		ItemStack stack;
		if (input instanceof Item) {
			stack = new ItemStack((Item) input);
		} else if (input instanceof Block) {
			stack = new ItemStack((Block) input);
		} else if (input instanceof ItemStack) {
			stack = (ItemStack) input;
		} else {
			throw new IllegalArgumentException("Only items, blocks, itemstacks...");
		}
		CoreUtil.gameregistry.addSmelting(stack, output, xp);
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
	 * Register a new tile entity. Usefull to implement #ICustomTileEntity
	 * 
	 * @param tileEntityClass Tile entity class
	 */
	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {

		try {
			if (Arrays.asList(tileEntityClass.getInterfaces()).contains(ICustomTileEntity.class)) {
				Method registryname = tileEntityClass.getMethod("getRegisterName");
				String id = (String) registryname.invoke(tileEntityClass.newInstance());
				registerTileEntity(tileEntityClass, id);
			} else {
				registerTileEntity(tileEntityClass, tileEntityClass.getName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Register a new tile entity
	 * 
	 * @param tileEntityClass Tile entity class
	 * @param id Tile entity id
	 */
	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
		CoreUtil.gameregistry.registerTileEntity(tileEntityClass, id);
	}

}
