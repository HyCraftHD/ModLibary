package net.hycrafthd.corelib.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {

	public static void register(Block block, String name) {
		register(block, ItemBlock.class, name);
	}

	public static void register(Block block, Class<? extends ItemBlock> itemblock, String name) {
		block.setUnlocalizedName(name);
		GameRegistry.registerBlock(block, itemblock, name);
	}

}
