package net.hycrafthd.corelib.util;

import net.minecraft.item.*;

/**
 * Util methods for {@link Item}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class ItemUtil {

	/**
	 * Get item from object
	 * 
	 * @param obj
	 *            Object
	 * @return Item or null if not found
	 */
	public static Item from(Object obj) {
		ItemStack stack = ItemStackUtil.from(obj);
		if (stack == null) {
			return null;
		}
		return stack.getItem();
	}

}
