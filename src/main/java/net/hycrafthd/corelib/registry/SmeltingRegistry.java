package net.hycrafthd.corelib.registry;

import org.apache.commons.lang3.Validate;

import net.hycrafthd.corelib.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingRegistry {

	public static void register(Object output, Object input, int xp) {
		ItemStack inputstack = ItemStackUtil.from(input);
		ItemStack outputstack = ItemStackUtil.from(output);
		GameRegistry.addSmelting(inputstack, outputstack, xp);
	}

}
