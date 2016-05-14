package net.hycrafthd.corelib.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static void register(Item item, String name) {
		item.setUnlocalizedName(name);
		GameRegistry.registerItem(item, name);
	}

}
