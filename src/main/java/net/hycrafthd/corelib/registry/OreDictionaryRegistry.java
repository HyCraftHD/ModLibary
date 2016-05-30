package net.hycrafthd.corelib.registry;

import net.hycrafthd.corelib.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryRegistry {

	public static void register(Object obj) {
		ItemStack stack = ItemStackUtil.from(obj);
		OreDictionary.registerOre(ItemStackUtil.getName(stack), stack);
	}

}
