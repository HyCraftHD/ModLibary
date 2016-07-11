package net.hycrafthd.corelib.registry;

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
	public static void registerEntity(Class<? extends Entity> entityClass, IRenderFactory<? super Entity> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}
	
	/**
	 * Register special tileentity renderer
	 * 
	 * @param tileEntityClass
	 *            Tileentity class
	 * @param specialRenderer
	 *            SpecialRender class
	 */
	public static void bindTileEntitySpecialRenderer(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer<? super TileEntity> specialRenderer) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}
	
}
