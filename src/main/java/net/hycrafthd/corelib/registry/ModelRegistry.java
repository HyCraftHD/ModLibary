package net.hycrafthd.corelib.registry;

import net.hycrafthd.corelib.util.ItemStackUtil;
import net.hycrafthd.corelib.util.ItemUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRegistry {

	public static void register(Object obj) {
		register(obj, 0);
	}

	public static void register(Object obj, int meta) {
		register(ItemUtil.from(obj), meta, new ModelResourceLocation(ItemStackUtil.getRegistryName(obj), "inventory"));
	}

	public static void register(Item item, int meta, ModelResourceLocation location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, location);
	}

	public static void registerVariants(Object obj, String... names) {
		ModelBakery.addVariantName(ItemUtil.from(obj), names);
	}

}
