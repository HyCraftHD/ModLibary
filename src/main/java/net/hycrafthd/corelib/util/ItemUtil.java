package net.hycrafthd.corelib.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUtil {

	public static Item from(Object obj) {
		ItemStack stack = ItemStackUtil.from(obj);
		if (stack == null) {
			return null;
		}
		return stack.getItem();
	}

}
