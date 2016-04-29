package net.hycrafthd.core.v1_8;

import net.hycrafthd.core.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * <h1>Never use this class direct! Use {@link ClientRegistry} instead!</h1>
 */
@SideOnly(Side.CLIENT)
public class Client {

	public void bindTileEntitySpecialRenderer(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer specialRenderer) {
		net.minecraftforge.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}

	public void registerKeyBinding(KeyBinding key) {
		net.minecraftforge.fml.client.registry.ClientRegistry.registerKeyBinding(key);
	}

	public void registerModel(Item item, Integer meta, String location, String variant) {
		ModelResourceLocation res = new ModelResourceLocation(location, variant);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, res);
	}

}
