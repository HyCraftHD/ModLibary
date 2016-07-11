package net.hycrafthd.corelib.util;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;

/**
 * Util methods for {@link ItemStack}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class ItemStackUtil {

	/**
	 * Get itemstack from object
	 * 
	 * @param obj
	 *            Object
	 * @return Itemstack or null if not found
	 */
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

	/**
	 * Get registry name for object
	 * 
	 * @param obj
	 *            Object
	 * @return Registry name or null if not found
	 */
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

	/**
	 * Get modid for object
	 * 
	 * @param obj
	 *            Object
	 * @return Modid or null if not found
	 */
	public static String getModID(Object obj) {
		return getRegistryName(obj) != null ? getRegistryName(obj).split(":")[0] : null;
	}

	/**
	 * Get name for object
	 * 
	 * @param obj
	 *            Object
	 * @return Name or null if not found
	 */
	public static String getName(Object obj) {
		return getRegistryName(obj) != null ? getRegistryName(obj).split(":")[1] : null;
	}
}
