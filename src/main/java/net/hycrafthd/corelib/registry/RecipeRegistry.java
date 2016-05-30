package net.hycrafthd.corelib.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry {

	public static void registerShaped(ItemStack output, Object... params) {
		GameRegistry.addShapedRecipe(output, params);
	}

	public static void registerShapeless(ItemStack output, Object... params) {
		GameRegistry.addShapelessRecipe(output, params);
	}

}
