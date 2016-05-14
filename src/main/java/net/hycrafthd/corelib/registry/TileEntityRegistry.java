package net.hycrafthd.corelib.registry;

import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry {

	public static void register(Class<? extends TileEntity> tileEntityClass) {
		Map<String, Class<?>> teMappings = ObfuscationReflectionHelper.getPrivateValue(TileEntity.class, null, "field_145855_i", "nameToClassMap");
		int id = teMappings.size() + 1;
		register(tileEntityClass, tileEntityClass.getName() + "-" + id);
	}

	public static void register(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
	}

}
