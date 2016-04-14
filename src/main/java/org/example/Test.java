package org.example;

import net.hycrafthd.core.ClientRegistry;
import net.hycrafthd.core.CommonRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Only for testings, will be removed further
 */

@Mod(modid = "test")
public class Test {

	public static Item testitem = new Item();
	public static Block testblock = new Block(Material.rock);

	@EventHandler
	public void f(FMLInitializationEvent event) {
		CommonRegistry.registerItem(testitem, "testitem");
		CommonRegistry.registerBlock(testblock, "testblock");
		ClientRegistry.registerModel(testitem);
		ClientRegistry.registerModel(testblock);

		CommonRegistry.addShapedRecipe(new ItemStack(testitem), new Object[] { "x", 'x', Blocks.dirt });
		CommonRegistry.addShapelessRecipe(new ItemStack(testitem), Items.diamond);
		CommonRegistry.addSmelting(testblock, new ItemStack(testitem), 1.0F);

		CommonRegistry.registerTileEntity(TestTileEntity.class);

	}

	// for (Method methodes : gameregistryclass.getMethods()) {
	// String s = methodes.getName();
	// for (Class clazz : methodes.getParameterTypes()) {
	// s = s + " : " + clazz;
	// }
	// System.out.println(s);
	// }

}
