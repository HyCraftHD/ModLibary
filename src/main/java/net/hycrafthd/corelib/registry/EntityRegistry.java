package net.hycrafthd.corelib.registry;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

/**
 * Registry for {@link Entity}. For {@link TileEntity} please use {@link TileEntityRegistry}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class EntityRegistry {

	/**
	 * Register a new Entity without spawneggs and generated unique id. May be deprecated in further versions!
	 * 
	 * @param entityClass
	 *            Class extends Entity
	 * @param entityName
	 *            Entity name
	 * @param mod
	 *            Mod instance or modid
	 * @param trackingRange
	 *            The range an entity should be tracked and updated
	 * @param updateFrequency
	 *            How often an entity should be updated
	 * @param sendsVelocityUpdates
	 *            Should the entity sends velocity updated
	 */
	public static void register(Class<? extends Entity> entityClass, String entityName, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, net.minecraftforge.fml.common.registry.EntityRegistry.findGlobalUniqueEntityId(), mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	/**
	 * Register a new Entity without spawneggs.
	 * 
	 * @param entityClass
	 *            Class extends Entity
	 * @param entityName
	 *            Entity name
	 * @param id
	 *            Entity id
	 * @param mod
	 *            Mod instance or modid
	 * @param trackingRange
	 *            The range an entity should be tracked and updated
	 * @param updateFrequency
	 *            How often an entity should be updated
	 * @param sendsVelocityUpdates
	 *            Should the entity sends velocity updated
	 */
	public static void register(Class<? extends Entity> entityClass, String entityName, Integer id, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	/**
	 * Register a new Entity with spawneggs and generated unique id. May be deprecated in further versions!
	 * 
	 * @param entityClass
	 *            Class extends Entity
	 * @param entityName
	 *            Entity name
	 * @param mod
	 *            Mod instance or modid
	 * @param trackingRange
	 *            The range an entity should be tracked and updated
	 * @param updateFrequency
	 *            How often an entity should be updated
	 * @param sendsVelocityUpdates
	 *            Should the entity sends velocity updated
	 * @param eggPrimary
	 *            Primary color of spawnegg
	 * @param eggSecondary
	 *            Secondary color of spawnegg
	 */
	public static void registerWithEggs(Class<? extends Entity> entityClass, String entityName, Integer id, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates, Integer eggPrimary, Integer eggSecondary) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}

	/**
	 * Register a new Entity with spawneggs.
	 * 
	 * @param entityClass
	 *            Class extends Entity
	 * @param entityName
	 *            Entity name
	 * @param id
	 *            Entity id
	 * @param mod
	 *            Mod instance or modid
	 * @param trackingRange
	 *            The range an entity should be tracked and updated
	 * @param updateFrequency
	 *            How often an entity should be updated
	 * @param sendsVelocityUpdates
	 *            Should the entity sends velocity updated
	 * @param eggPrimary
	 *            Primary color of spawnegg
	 * @param eggSecondary
	 *            Secondary color of spawnegg
	 */
	public static void registerWithEggs(Class<? extends Entity> entityClass, String entityName, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates, Integer eggPrimary, Integer eggSecondary) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, net.minecraftforge.fml.common.registry.EntityRegistry.findGlobalUniqueEntityId(), mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}

}
