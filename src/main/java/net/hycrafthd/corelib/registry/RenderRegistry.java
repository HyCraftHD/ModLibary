package net.hycrafthd.corelib.registry;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.relauncher.*;

/**
 * Registry for renderer ({@link RenderingRegistry}, {@link ClientRegistry}) (CLIENT SIDE ONLY)
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SideOnly(Side.CLIENT)
public class RenderRegistry {
	
	/**
	 * Register renderer for entity
	 * 
	 * @param entityClass
	 *            Class extends entity
	 * @param renderer
	 *            Render instance
	 */
	public static void registerEntity(Class<? extends Entity> entityClass, Render<? extends Entity> renderer) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderer);
	}
	
	/**
	 * Register special tileentity renderer
	 * 
	 * @param tileEntityClass
	 *            Tileentity class
	 * @param specialRenderer
	 *            SpecialRender class
	 */
	public static <T extends TileEntity> void bindTileEntitySpecialRenderer(Class<T> tileEntityClass, TileEntitySpecialRenderer<T> specialRenderer) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}
	
}
