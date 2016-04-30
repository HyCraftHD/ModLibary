package net.hycrafthd.core;

import net.hycrafthd.core.util.ClassObject;
import net.hycrafthd.core.util.CoreUtil;
import net.hycrafthd.core.util.ItemUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ClientRegistry! Only on client side
 */
@SideOnly(Side.CLIENT)
public class ClientRegistry {

	/**
	 * Bind special tileentity renderer
	 * 
	 * @param tileEntityClass TileEntity class
	 * @param specialRenderer TileEntitySpecialRenderer class
	 */
	public static void bindTileEntitySpecialRenderer(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer specialRenderer) {
		net.minecraftforge.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}

	/**
	 * Register an entity render <br>
	 * <br>
	 * Constructor: <br>
	 * <br>
	 * public classname(RenderManager) { <br>
	 * <br>
	 * } <br>
	 * 
	 * @param entityClass Entity class
	 * @param renderClass Render class
	 */
	public static void registerEntityRenderer(Class<? extends Entity> entityClass, Class<? extends Render> renderClass) {
		CoreUtil.invokeMethod("Client", "registerEntityRenderer", ClassObject.forObj(entityClass, renderClass));
	}

	/**
	 * Register variants for a item
	 * 
	 * @param item Item instance
	 * @param names Resource names
	 */
	public static void registerItemVariants(Item item, String... names) {
		CoreUtil.invokeMethod("Client", "registerItemVariants", ClassObject.forObj(item, names));
	}

	/**
	 * Register keybindings
	 * 
	 * @param key Keybinding
	 */
	public void registerKeyBinding(KeyBinding key) {
		net.minecraftforge.fml.client.registry.ClientRegistry.registerKeyBinding(key);
	}

	/**
	 * Register a new model for rendering items and blocks. This method takes the unlocalized name to register the model and only is for items that have no meta
	 * 
	 * @param object Item or Block instance
	 */
	public static void registerModel(Object object) {
		registerModel(object, 0);
	}

	/**
	 * Register a new model for rendering items and blocks. This method takes the unlocalized name to register the model
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

		registerModel(item, meta, ItemUtil.getModid(item) + ":" + item.getUnlocalizedName().substring(5), "inventory");

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
		CoreUtil.invokeMethod("Client", "registerModel", ClassObject.forObj(item, meta, location, variant));
	}

}
