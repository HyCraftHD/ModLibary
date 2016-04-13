package org.example;

import net.hycrafthd.core.ClientRegistry;
import net.hycrafthd.core.CommonRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

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
	}

	// for (Method methodes : gameregistryclass.getMethods()) {
	// String s = methodes.getName();
	// for (Class clazz : methodes.getParameterTypes()) {
	// s = s + " : " + clazz;
	// }
	// System.out.println(s);
	// }

}
