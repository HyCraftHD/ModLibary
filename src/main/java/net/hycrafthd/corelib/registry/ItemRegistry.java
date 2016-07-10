package net.hycrafthd.corelib.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Register for {@link Item}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class ItemRegistry {

	/**
	 * Register a new item
	 * 
	 * @param item
	 *            Item instance
	 * @param name
	 *            Item name
	 */
	public static void register(Item item, String name) {
		item.setRegistryName(name);
		GameRegistry.register(item);
	}

}
