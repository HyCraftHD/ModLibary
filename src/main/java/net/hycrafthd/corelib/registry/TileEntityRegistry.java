package net.hycrafthd.corelib.registry;

import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registry for {@link TileEntity}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class TileEntityRegistry {

	/**
	 * Register a tileentity
	 * 
	 * @param tileEntityClass
	 *            Class extends tileentity
	 */
	public static void register(Class<? extends TileEntity> tileEntityClass) {
		Map<String, Class<?>> teMappings = ObfuscationReflectionHelper.getPrivateValue(TileEntity.class, null, "field_145855_i", "nameToClassMap");
		int id = teMappings.size() + 1;
		register(tileEntityClass, tileEntityClass.getName() + "-" + id);
	}

	/**
	 * Register a tileentity
	 * 
	 * @param tileEntityClass
	 *            Class extends tileentity
	 * @param id
	 *            Tileentity id
	 */
	public static void register(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
	}

}
