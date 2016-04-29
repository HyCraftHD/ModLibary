package org.example;

import java.nio.FloatBuffer;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntitySpecialRendererTest extends TileEntitySpecialRenderer {

	private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");

	public void draw(TestTileEntity tile, double translationX, double translationY, double translationZ, float f, int i) {

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) translationX + 0.5F, (float) translationY + 1.5F, (float) translationZ + 0.5F);

		float f1 = 0.4375F;
		GlStateManager.scale(f1, f1, f1);

		Entity entity = Minecraft.getMinecraft().thePlayer;

		Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, i);

		GlStateManager.popMatrix();

	}

	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posZ, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
		this.draw((TestTileEntity) p_180535_1_, posX, posZ, p_180535_6_, p_180535_8_, p_180535_9_);
	}
}