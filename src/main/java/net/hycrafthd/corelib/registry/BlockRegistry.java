package net.hycrafthd.corelib.registry;

import org.apache.commons.lang3.Validate;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {

	public static void register(Block block, String name) {
		register(block, ItemBlock.class, name);
	}

	public static void register(Block block, Class<? extends ItemBlock> itemblock, String name) {
		GameRegistry.registerBlock(block, itemblock, name);
	}

}
