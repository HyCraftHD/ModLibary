package net.hycrafthd.corelib.registry;

import net.hycrafthd.corelib.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Registry for {@link OreDictionary}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class OreDictionaryRegistry {

	/**
	 * Register an ore to ore dictionary with name from {@code ItemStackUtil.getName(stack)}
	 * 
	 * @param obj
	 *            Item or block instance
	 */
	public static void register(Object obj) {
		ItemStack stack = ItemStackUtil.from(obj);
		OreDictionary.registerOre(ItemStackUtil.getName(stack), stack);
	}

}
