package net.hycrafthd.corelib.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registry for crafting recipes {@link GameRegistry}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class RecipeRegistry {

	/**
	 * Register a new shaped crafting recipe
	 * 
	 * @param output
	 *            Output itemstack
	 * @param params
	 *            Object for arrangement (input)
	 */
	public static void registerShaped(ItemStack output, Object... params) {
		GameRegistry.addShapedRecipe(output, params);
	}

	/**
	 * Register a new shapeless crafting recipe
	 * 
	 * @param output
	 *            Output itemstack
	 * @param params
	 *            Object for itemstacks or items or blocks (input)
	 */
	public static void registerShapeless(ItemStack output, Object... params) {
		GameRegistry.addShapelessRecipe(output, params);
	}

}
