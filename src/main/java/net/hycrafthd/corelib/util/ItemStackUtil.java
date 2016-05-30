package net.hycrafthd.corelib.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemStackUtil {

	public static ItemStack from(Object obj) {
		ItemStack stack = null;
		if (obj instanceof ItemStack) {
			stack = (ItemStack) obj;
		} else if (obj instanceof Item) {
			stack = new ItemStack((Item) obj);
		} else if (obj instanceof Block) {
			stack = new ItemStack((Block) obj);
		} else if (obj instanceof String || obj instanceof Integer) {
			String s;
			if (obj instanceof Integer) {
				s = String.valueOf((String) obj);
			} else {
				s = (String) obj;
			}
			Item item = Item.getByNameOrId(s);
			Block block = Block.getBlockFromName(s);
			if (item != null) {
				stack = new ItemStack(item);
			} else if (block != null) {
				stack = new ItemStack(block);
			}
		}
		return stack;
	}
	
	public static String getRegistryName(Object obj) {
		Object name = Item.itemRegistry.getNameForObject(from(obj).getItem());
		if (name instanceof String) {
			return (String) name;
		}
		if (name instanceof ResourceLocation) {
			return ((ResourceLocation) name).toString();
		}
		return null;
	}

	public static String getModID(Object obj) {
		return getRegistryName(obj).split(":")[0];
	}

	public static String getName(Object obj) {
		return getRegistryName(obj).split(":")[1];
	}
}
