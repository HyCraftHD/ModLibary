package net.hycrafthd.corelib.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.*;

/**
 * Refer to {@link ForgeHooks}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class CommonForgeHooks extends ForgeHooks {

	/**
	 * Added Methods from {@link MinecraftForge}
	 *
	 * Register a new seed to be dropped when breaking tall grass.
	 * 
	 * @see {@link MinecraftForge}
	 * 
	 * @param seed
	 *            The item to drop as a seed.
	 * @param weight
	 *            The relative probability of the seeds, where wheat seeds are 10.
	 */
	public static void addGrassSeed(ItemStack seed, int weight) {
		MinecraftForge.addGrassSeed(seed, weight);
	}

}
