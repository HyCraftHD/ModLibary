package net.hycrafthd.core.v1_8;

import net.hycrafthd.core.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * <h1>Never use this class direct! Use {@link ClientRegistry} instead!</h1>
 */
@SideOnly(Side.CLIENT)
public class Client {

	public static void registerEntityRenderer(Class<? extends Entity> entityClass, Class<? extends Render> renderClass) {
		try {
			RenderingRegistry.registerEntityRenderingHandler(entityClass, renderClass.getConstructor(RenderManager.class).newInstance(Minecraft.getMinecraft().getRenderManager()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void registerItemVariants(Item item, String[] names) {
		for (String name : names) {
			ModelBakery.addVariantName(item, names);
		}
	}

	public void registerModel(Item item, Integer meta, String location, String variant) {
		ModelResourceLocation res = new ModelResourceLocation(location, variant);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, res);
	}

}
