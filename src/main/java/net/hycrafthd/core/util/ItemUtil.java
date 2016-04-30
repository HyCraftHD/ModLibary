package net.hycrafthd.core.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemUtil {

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

	public static String getModid(Object obj) {
		String modid = "minecraft";
		Object name = Item.itemRegistry.getNameForObject(getItemStack(obj).getItem());

		if (name instanceof String) {
			String[] parts = ((String) name).split(":");
			modid = parts[0];
		}
		if (name instanceof ResourceLocation) {
			modid = ((ResourceLocation) name).getResourceDomain();
		}
		return modid;
	}

}
