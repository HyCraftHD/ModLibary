package net.hycrafthd.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.hycrafthd.core.exeption.UnsupportedVersionExeption;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonRegistry {

	private static String version = ForgeVersion.mcVersion;
	private static Class gameregistryclass;

	public static void registerItem(Item item, String itemname) {

		if (version == "1.8" || version == "1.8.8" || version == "1.8.9") {
			try {

				Method unlocalizedname = item.getClass().getMethod("setUnlocalizedName", String.class);
				Method register = gameregistryclass.getMethod("registerItem", Item.class, String.class);

				unlocalizedname.invoke(item, itemname);
				register.invoke(null, item, itemname);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (version == "1.9") {
			try {

				Class iforgeregistry = Class.forName("net.minecraftforge.fml.common.registry.IForgeRegistryEntry");

				Method unlocalizedname = item.getClass().getMethod("setUnlocalizedName", String.class);
				Method registryname = item.getClass().getMethod("setRegistryName", String.class);
				Method register = gameregistryclass.getMethod("register", iforgeregistry);

				unlocalizedname.invoke(item, itemname);
				registryname.invoke(item, itemname);
				register.invoke(iforgeregistry, item);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			throw new UnsupportedVersionExeption();
		}
	}

	public static void registerBlock(Block block, String blockname) {
		registerBlock(block, ItemBlock.class, blockname);
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemblock, String blockname) {

		if (version == "1.8" || version == "1.8.8" || version == "1.8.9") {
			try {

				Method unlocalizedname = block.getClass().getMethod("setUnlocalizedName", String.class);
				Method register = gameregistryclass.getMethod("registerBlock", Block.class, Class.class, String.class);

				unlocalizedname.invoke(block, blockname);
				register.invoke(null, block, itemblock, blockname);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (version == "1.9") {
			try {

				Class iforgeregistry = Class.forName("net.minecraftforge.fml.common.registry.IForgeRegistryEntry");

				Constructor<? extends ItemBlock> constructor = itemblock.getConstructor(Block.class);
				ItemBlock itemblockinstance = constructor.newInstance(block);

				Method unlocalizedname = block.getClass().getMethod("setUnlocalizedName", String.class);
				Method registryname = block.getClass().getMethod("setRegistryName", String.class);
				Method unlocalizedname2 = itemblockinstance.getClass().getMethod("setUnlocalizedName", String.class);
				Method registryname2 = itemblockinstance.getClass().getMethod("setRegistryName", String.class);
				Method register = gameregistryclass.getMethod("register", iforgeregistry);

				unlocalizedname.invoke(block, blockname);
				registryname.invoke(block, blockname);
				unlocalizedname2.invoke(itemblockinstance, blockname);
				registryname2.invoke(itemblockinstance, blockname);
				register.invoke(iforgeregistry, block);
				register.invoke(iforgeregistry, itemblockinstance);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			throw new UnsupportedVersionExeption();
		}
	}

	static {
		try {
			gameregistryclass = Class.forName("net.minecraftforge.fml.common.registry.GameRegistry");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			FMLCommonHandler.instance().exitJava(0, true);
		}
	}
}
