package net.hycrafthd.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientRegistry {

	/**
	 * Register a new model for rendering items and blocks. This method takes
	 * the unlocalized name to register the model and only is for items that
	 * have no meta
	 * 
	 * @param object Item or Block instance
	 */
	public static void registerModel(Object object) {
		registerModel(object, 0);
	}

	/**
	 * Register a new model for rendering items and blocks. This method takes
	 * the unlocalized name to register the model
	 * 
	 * @param object Item or Block instance
	 * @param meta Item meta
	 */
	public static void registerModel(Object object, int meta) {

		Item item = null;

		if (object instanceof Item) {
			item = (Item) object;
		} else if (object instanceof Block) {
			item = Item.getItemFromBlock((Block) object);
		} else {
			throw new IllegalArgumentException("Only items and block objects are allowed");
		}

		String modid = "minecraft";
		Object obj = item.itemRegistry.getNameForObject(item);

		if (obj instanceof String) {
			String[] parts = ((String) obj).split(":");
			modid = parts[0];
		}
		if (obj instanceof ResourceLocation) {
			modid = ((ResourceLocation) obj).getResourceDomain();
		}

		registerModel(item, meta, modid + ":" + item.getUnlocalizedName().substring(5), "inventory");

	}

	/**
	 * Register a new model for rendering items and blocks
	 * 
	 * @param item Item instance
	 * @param meta Item meta
	 * @param location Resource location
	 * @param variant Variant, normally inventory
	 */
	public static void registerModel(Item item, int meta, String location, String variant) {

		if (!CoreUtil.isUnsupportedVersion()) {
			try {

				Class modelresourcelocation = null;

				if (CoreUtil.contains1_8()) {
					modelresourcelocation = Class.forName("net.minecraft.client.resources.model.ModelResourceLocation");
				} else if (CoreUtil.contains1_9()) {
					modelresourcelocation = Class.forName("net.minecraft.client.renderer.block.model.ModelResourceLocation");
				}

				if (modelresourcelocation != null) {

					Constructor constructor = modelresourcelocation.getConstructor(String.class, String.class);
					Method method = ItemModelMesher.class.getMethod("register", Item.class, int.class, modelresourcelocation);

					method.invoke(Minecraft.getMinecraft().getRenderItem().getItemModelMesher(), item, meta, constructor.newInstance(location, variant));

				} else {
					throw new ClassNotFoundException();
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
