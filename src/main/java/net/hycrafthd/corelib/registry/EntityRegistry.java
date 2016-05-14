package net.hycrafthd.corelib.registry;

import net.minecraft.entity.Entity;

public class EntityRegistry {

	public void register(Class<? extends Entity> entityClass, String entityName, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, net.minecraftforge.fml.common.registry.EntityRegistry.findGlobalUniqueEntityId(), mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	public void register(Class<? extends Entity> entityClass, String entityName, Integer id, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	public void register(Class<? extends Entity> entityClass, String entityName, Integer id, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates, Integer eggPrimary, Integer eggSecondary) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}

	public void register(Class<? extends Entity> entityClass, String entityName, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates, Integer eggPrimary, Integer eggSecondary) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entityClass, entityName, net.minecraftforge.fml.common.registry.EntityRegistry.findGlobalUniqueEntityId(), mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}

}
