package net.hycrafthd.corelib.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Utils for Colors ({@link NBTTagCompound} , {@link RGBA})
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class ColorUtils {

	/**
	 * Read color code from itemstack
	 * 
	 * @param stack
	 *            Itemstack
	 * @return colorcode
	 */
	public static int getColor(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound != null) {
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3)) {
				return nbttagcompound1.getInteger("color");
			}
		}
		return 4860944;
	}

	/**
	 * Remove current color from itemstack
	 * 
	 * @param stack
	 *            Itemstack
	 */
	public static void removeColor(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound != null) {
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1.hasKey("color")) {
				nbttagcompound1.removeTag("color");
			}
		}
	}

	/**
	 * Set Color to itemstack
	 * 
	 * @param stack
	 *            Itemstack
	 * @param color
	 *            Colorcode
	 */
	public static void setColor(ItemStack stack, int color) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound == null) {
			nbttagcompound = new NBTTagCompound();
			stack.setTagCompound(nbttagcompound);
		}
		NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

		if (!nbttagcompound.hasKey("display", 10)) {
			nbttagcompound.setTag("display", nbttagcompound1);
		}
		nbttagcompound1.setInteger("color", color);
	}

	/**
	 * Converts int colorcodes to RGB
	 * 
	 * @param color
	 *            colorcode
	 * @return RGBA
	 */
	public static RGBA hexToRGBA(int color) {
		int f3 = Math.round((float) (color >> 24 & 255) / 255.0F);
		int f = Math.round((float) (color >> 16 & 255) / 255.0F);
		int f1 = Math.round((float) (color >> 8 & 255) / 255.0F);
		int f2 = Math.round((float) (color & 255) / 255.0F);
		return new RGBA(f, f1, f2, f3);
	}

}
