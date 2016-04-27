package net.hycrafthd.core.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemstackUtil {

	public static ItemStack getItemStack(Object obj) {
		ItemStack stack;
		if (obj instanceof Item) {
			stack = new ItemStack((Item) obj);
		} else if (obj instanceof Block) {
			stack = new ItemStack((Block) obj);
		} else if (obj instanceof ItemStack) {
			stack = (ItemStack) obj;
		} else {
			throw new IllegalArgumentException("Only items, blocks, itemstacks are allowed!");
		}
		return stack;
	}

}
