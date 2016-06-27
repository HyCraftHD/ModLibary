package net.hycrafthd.corelib.registry;

import net.hycrafthd.corelib.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registry for smelting recipes {@link GameRegistry}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class SmeltingRegistry {

	/**
	 * Register a new furnace recipe
	 * 
	 * @param output
	 *            Output itemstack
	 * @param input
	 *            Input itemstack
	 * @param xp
	 *            Xp per smelting
	 */
	public static void register(Object output, Object input, int xp) {
		ItemStack inputstack = ItemStackUtil.from(input);
		ItemStack outputstack = ItemStackUtil.from(output);
		GameRegistry.addSmelting(inputstack, outputstack, xp);
	}

}
