package net.hycrafthd.core.v1_8;

import net.hycrafthd.core.CommonRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * <h1>Never use this class direct! Use {@link CommonRegistry} instead!</h1>
 */
public class Common {

	public void addShapedRecipe(ItemStack output, Object... obj) {
		GameRegistry.addShapedRecipe(output, obj);
	}

	public void addShapelessRecipe(ItemStack output, Object... obj) {
		GameRegistry.addShapelessRecipe(output, obj);
	}

	public void addSmelting(ItemStack input, ItemStack output, Float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public void registerBlock(Block block, Class<? extends ItemBlock> itemblock, String blockname) {
		block.setUnlocalizedName(blockname);
		GameRegistry.registerBlock(block, itemblock, blockname);
	}

	public void registerEntity(Class<? extends Entity> entityClass, String entityName, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, entityName, EntityRegistry.findGlobalUniqueEntityId(), mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	public void registerEntity(Class<? extends Entity> entityClass, String entityName, Integer id, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	public void registerEntity(Class<? extends Entity> entityClass, String entityName, Integer id, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates, Integer eggPrimary, Integer eggSecondary) {
		EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}

	public void registerEntity(Class<? extends Entity> entityClass, String entityName, String mod, Integer trackingRange, Integer updateFrequency, Boolean sendsVelocityUpdates, Integer eggPrimary, Integer eggSecondary) {
		EntityRegistry.registerModEntity(entityClass, entityName, EntityRegistry.findGlobalUniqueEntityId(), mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}

	public void registerItem(Item item, String itemname) {
		item.setUnlocalizedName(itemname);
		GameRegistry.registerItem(item, itemname);
	}

	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
	}
}
