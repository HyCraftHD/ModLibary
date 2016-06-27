package net.hycrafthd.corelib.registry;

import net.hycrafthd.corelib.util.ItemStackUtil;
import net.hycrafthd.corelib.util.ItemUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Registry for item and block models ({@link RenderItem}) (CLIENT SIDE ONLY)
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SideOnly(Side.CLIENT)
public class ModelRegistry {

	/**
	 * Register an item or block model with meta 0 and resource location from the registry name
	 * 
	 * @param obj
	 *            Item or block instance
	 */
	public static void register(Object obj) {
		register(obj, 0);
	}

	/**
	 * Register an item or block model with resource location from the registry name
	 * 
	 * @param obj
	 *            Item or block instance
	 * @param meta
	 *            Metadata
	 */
	public static void register(Object obj, int meta) {
		register(ItemUtil.from(obj), meta, new ModelResourceLocation(ItemStackUtil.getRegistryName(obj), "inventory"));
	}

	/**
	 * Register an item model with meta and model resource location
	 * 
	 * @param item
	 *            Item instance or block with {@code ItemUtil.from(blockvar)}
	 * @param meta
	 *            Metadata
	 * @param location
	 *            Model resource location
	 */
	public static void register(Item item, int meta, ModelResourceLocation location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, location);
	}

	/**
	 * Register model variants for metadata
	 * 
	 * @param obj
	 *            Item or block instance
	 * @param names
	 *            Resourcelocations like minecraft:stone
	 */
	public static void registerVariants(Object obj, String... names) {
		ModelBakery.addVariantName(ItemUtil.from(obj), names);
	}

}
