package net.hycrafthd.corelib.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.corelib.via.Vertex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class LWJGLUtils {
	
	/**
	 * Conduit Render now in ItemRenderer (Minecraft)
	 */
	
	/**
	 * @deprecated
	 * Draws a Horizontal Line<br>
	 * <br>
	 * x = x Start Position of Line <br>
	 * x2 = x End Position of Line <br>
	 * y = y Position of Line <br>
	 * r = your Color as {@link RGBA}
	 * 
	 * @param
	 * 
	 */
	@Deprecated
	public static void drawHLine(double x, double x2, double y, RGBA r) {
		drawGradientRect(x, y, x2, y + 1, r, r);
	}
	
	/**
	 * @deprecated
	 * Draws a Vertical Line<br>
	 * <br>
	 * x = x Position of Line <br>
	 * y = y Start Position of Line <br>
	 * down = y End Position of Line <br>
	 * r = your Color as {@link RGBA}
	 * 
	 * @param
	 * 
	 */
	@Deprecated
	public static void drawVLine(double x, double y, double down, RGBA r) {
		drawGradientRect(x, y, x + 1, down, r, r);
	}
	
	/**
	 * @deprecated
	 * Draws a Horizontal Line with Z Pos<br>
	 * <br>
	 * x = x Start Position of Line <br>
	 * x2 = x End Position of Line <br>
	 * y = y Position of Line <br>
	 * r = your Color as {@link RGBA} <br>
	 * z = z Position in 3D Rooms only
	 * 
	 * @param
	 */
	@Deprecated
	public static void drawHLine(double x, double x2, double y, RGBA r, double z) {
		drawGradientRect(x, y, x2, y + 1, r, r, z);
	}
	
	/**
	 * @deprecated
	 * Draws a Vertical Line with Z Pos<br>
	 * <br>
	 * x = x Position of Line <br>
	 * y = y Start Position of Line <br>
	 * down = y End Position of Line <br>
	 * r = your Color as {@link RGBA} <br>
	 * z = z Position in 3D Rooms only
	 * 
	 * @param
	 */
	@Deprecated
	public static void drawVLine(double x, double y, double down, RGBA r, double z) {
		drawGradientRect(x, y, x + 1, down, r, r, z);
	}
	
	/**
	 * Draws a given Region
	 * 
	 * <br>
	 * rgb = Color as {@link RGBA}
	 * 
	 * @param
	 */
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA rgb, double z) {
		drawGradientRect(left, top, right, bottom, rgb, rgb, z);
	}
	
	/**
	 * Draws a given Region
	 * 
	 * <br>
	 * rgb = Color as {@link RGBA}
	 * 
	 * @param
	 */
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA rgb) {
		drawGradientRect(left, top, right, bottom, rgb, rgb);
	}
	
	/**
	 * Draws a given Region
	 * 
	 * <br>
	 * start = Start Color as {@link RGBA} <br>
	 * end = End Color as {@link RGBA} <br>
	 * <br>
	 * Just try out how the Start or End Color effects your draw
	 * 
	 * @param
	 */
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA start, RGBA end) {
		drawGradientRect(left, top, right, bottom, start, end, 0);
	}
	
	/**
	 * Draws a given Region
	 * 
	 * <br>
	 * start = Start Color as {@link RGBA} <br>
	 * end = End Color as {@link RGBA} <br>
	 * <br>
	 * Just try out how the Start or End Color effects your draw
	 * 
	 * @param
	 */
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA start, RGBA end, double z) {
		 GlStateManager.disableTexture2D();
		 GlStateManager.enableBlend();
		 GlStateManager.disableAlpha();
		 GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		 GlStateManager.shadeModel(7425);
		 Tessellator tessellator = Tessellator.getInstance();
		 VertexBuffer VertexBuffer = tessellator.getBuffer();
	     VertexBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		 VertexBuffer.pos((double) right, (double) top, (double) z).color(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha()).endVertex();
		 VertexBuffer.pos((double) left, (double) top, (double) z).color(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha()).endVertex();
		 VertexBuffer.pos((double) left, (double) bottom, (double) z).color(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha()).endVertex();
		 VertexBuffer.pos((double) right, (double) bottom, (double) z).color(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha()).endVertex();
		 tessellator.draw();
		 GlStateManager.shadeModel(7424);
		 GlStateManager.disableBlend();
		 GlStateManager.enableAlpha();
		 GlStateManager.enableTexture2D();
	}
	
	/**
	 * Draws a given Region with a Z multiplier<br>
	 * (means that you get a Curved Region)<br>
	 * <br>
	 * start = Start Color as {@link RGBA} <br>
	 * end = End Color as {@link RGBA} <br>
	 * <br>
	 * Just try out how the Start or End Color effects your draw
	 * 
	 * @param
	 */
	public static void drawGradientRectWithMultiplier(int left, int top, int right, int bottom, RGBA start, RGBA end, double z, double multiplier) {
		 double oldZ = z;
		 for (int i = 0; i < right; i++) {
		 z = z + (multiplier + (i * 0.005));
		 GlStateManager.pushMatrix();
		 GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		 GlStateManager.disableTexture2D();
		 GlStateManager.enableBlend();
		 GlStateManager.disableLighting();
		 GlStateManager.depthMask(false);
		 GlStateManager.enableBlend();
		 GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		 Tessellator tessellator = Tessellator.getInstance();
		 VertexBuffer VertexBuffer = tessellator.getBuffer();
	     VertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		 VertexBuffer.pos((double) left + i, (double) top, (double) oldZ).color(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha()).endVertex();
		 VertexBuffer.pos((double) left + 1 + i, (double) top, (double) z).color(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha()).endVertex();
		 VertexBuffer.pos((double) left + 1 + i, (double) bottom, (double) z).color(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha()).endVertex();
		 VertexBuffer.pos((double) left + i, (double) bottom, (double) oldZ).color(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha()).endVertex();
		 tessellator.draw();
		 GlStateManager.shadeModel(7424);
		 GlStateManager.disableBlend();
		 GlStateManager.enableAlpha();
		 GlStateManager.enableTexture2D();
		 GlStateManager.popMatrix();
		 GlStateManager.pushMatrix();
		 GlStateManager.rotate(180F, 0F, 1.0F, 0F);
		 if (left > 0) {
		 GlStateManager.translate(-left, 0, 0);
		 } else {
		 GlStateManager.translate(37, 0, 0);
		 }
		 GlStateManager.disableTexture2D();
		 GlStateManager.enableBlend();
	     VertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		 VertexBuffer.pos((double) left + i, (double) top, (double) -oldZ).color(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha()).endVertex();
		 VertexBuffer.pos((double) left + 1 + i, (double) top, (double) -z).color(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha()).endVertex();
		 VertexBuffer.pos((double) left + 1 + i, (double) bottom, (double) -z).color(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha()).endVertex();
		 VertexBuffer.pos((double) left + i, (double) bottom, (double) -oldZ).color(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha()).endVertex();
		 tessellator.draw();
		 GlStateManager.shadeModel(7424);
		 GlStateManager.disableBlend();
		 GlStateManager.enableAlpha();
		 GlStateManager.enableTexture2D();
		 GlStateManager.popMatrix();
		 oldZ = z;
		 }
	}
	
	/**
	 * @deprecated
	 * Draws a Frame of a given Region <br>
	 * <br>
	 * rgb = Start Color as {@link RGBA}
	 * 
	 * @param
	 */
	@Deprecated
	public static void drawFrame(double x, double y, double width, double height, RGBA rgb) {
		drawFrame(x, y, width, height, rgb, 0);
	}
	
	/**
	 * @deprecated
	 * Draws a Frame of a given Region <br>
	 * <br>
	 * rgb = Start Color as {@link RGBA}
	 * 
	 * @param
	 */
	@Deprecated
	public static void drawFrame(double x, double y, double width, double height, RGBA rgb, double z) {
		drawHLine(x - 1, x + width + 1, y - 1, rgb, z);
		drawHLine(x - 1, x + width + 1, y + height, rgb, z);
		drawVLine(x - 1, y, y + height, rgb, z);
		drawVLine(x + width, y, y + height, rgb, z);
	}
	
	/**
	 * @deprecated
	 * Draws a String in the World with Background and Frame <br>
	 * <br>
	 * start = Start Color as {@link RGBA} <br>
	 * end = End Color as {@link RGBA} <br>
	 * Frame = Frame Color as {@link RGBA} <br>
	 * color = Integer The Color of String
	 * 
	 * @param
	 */
	public static void drawStringInWorld(BlockPos pos, double posX, double posY, double posZ, int p_180535_9_, String text, RGBA start, RGBA end, RGBA frame, int color) {
		EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		BlockPos po = pl.getPosition();
		if (pos.getX() + 10 > po.getX() && pos.getX() - 10 < po.getX() && pos.getY() + 10 > po.getY() && pos.getY() - 10 < po.getY() && pos.getZ() + 10 > po.getZ() && pos.getZ() - 10 < po.getZ()) {
			FontRenderer fontrenderer = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
			float f = 1.6F;
			float f1 = 0.016666668F * f;
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) posX + 0.5F, (float) posY + 1.5F, (float) posZ + 0.5F);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(-f1, -f1, f1);
			GlStateManager.disableLighting();
			GlStateManager.depthMask(false);
			GlStateManager.disableDepth();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			byte b0 = 0;
			GlStateManager.disableTexture2D();
			int j = fontrenderer.getStringWidth(text) / 2;
			int j2 = fontrenderer.getStringWidth(text);
			drawGradientRect((double) (-j - 1), (double) (-1 + b0), (double) (j + 1), (double) (8 + b0), start, end);
			drawFrame((double) (-j - 1), (double) (-1 + b0), (double) (j2 + 1), (double) (8 + b0), frame);
			GlStateManager.enableTexture2D();
			fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, b0, color);
			GlStateManager.enableDepth();
			GlStateManager.depthMask(true);
			fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, b0, color);
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}
	
	/**
	 * Draws a String in the World <br>
	 * <br>
	 * color = Integer The Color of String
	 * 
	 * @param
	 */
	public static void drawOnlyStringInWorld(BlockPos pos, double posX, double posY, double posZ, String text, int color) {
		EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		BlockPos po = pl.getPosition();
		if (pos.getX() + 10 > po.getX() && pos.getX() - 10 < po.getX() && pos.getY() + 10 > po.getY() && pos.getY() - 10 < po.getY() && pos.getZ() + 10 > po.getZ() && pos.getZ() - 10 < po.getZ()) {
			FontRenderer fontrenderer = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
			float f = 1.6F;
			float f1 = 0.016666668F * f;
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) posX + 0.5F, (float) posY + 1.5F, (float) posZ + 0.5F);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(-f1, -f1, f1);
			GlStateManager.disableLighting();
			GlStateManager.depthMask(false);
			GlStateManager.disableDepth();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			byte b0 = 0;
			GlStateManager.disableTexture2D();
			GlStateManager.enableTexture2D();
			fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, b0, color);
			GlStateManager.enableDepth();
			GlStateManager.depthMask(true);
			fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, b0, color);
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}
	
	/**
	 * Draws something in the World <br>
	 * <br>
	 * color = Integer The Color of String <br>
	 * run = a Runnable with your stuff to Render
	 * 
	 * @param
	 */
	public static void drawSmThInWorld(BlockPos pos, double posX, double posY, double posZ, Runnable run) {
		EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		BlockPos po = pl.getPosition();
		if (pos.getX() + 10 > po.getX() && pos.getX() - 10 < po.getX() && pos.getY() + 10 > po.getY() && pos.getY() - 10 < po.getY() && pos.getZ() + 10 > po.getZ() && pos.getZ() - 10 < po.getZ()) {
			float f = 1.6F;
			float f1 = 0.016666668F * f;
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) posX + 0.5F, (float) posY + 1.5F, (float) posZ + 0.5F);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(-f1, -f1, f1);
			GlStateManager.disableLighting();
			GlStateManager.depthMask(false);
			GlStateManager.disableDepth();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.disableTexture2D();
			run.run();
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glNormal3f(1.0F, 1.0F, 1.0F);
			GlStateManager.enableDepth();
			GlStateManager.popMatrix();
		}
	}
	
	/**
	 * @deprecated
	 * Draws a Frame in the World <br>
	 * <br>
	 * frame = Color of Frame in {@link RGBA}
	 * 
	 * @param
	 */
	@Deprecated
	public static void drawFrameInWorld(BlockPos pos, double posX, double posY, double posZ, final double width, final double height, final RGBA frame) {
		drawSmThInWorld(pos, posX, posY, posZ, new Runnable() {
			
			@Override
			public void run() {
				GlStateManager.shadeModel(7425);
				GL11.glNormal3f(0.0F, 1.0F, 0.0F);
				GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
				GlStateManager.enableDepth();
				GlStateManager.color(1, 1, 1);
				drawFrame((double) (-width / 2 - 1), (double) (-1 + 0), (double) (width + 1), (double) (height + 0), frame);
				GlStateManager.enableDepth();
				GlStateManager.enableLighting();
				GlStateManager.shadeModel(7424);
			}
		});
	}
	
	/**
	 * Draws a Texture
	 * 
	 * @param
	 */
	public static void drawTexture(ResourceLocation location, double textureWidth, double textureHeight, double x, double y, double z, double width, double height, double u, double v) {
		 Minecraft.getMinecraft().getTextureManager().bindTexture(location);
		 double f4 = 1.0F / textureWidth;
		 double f5 = 1.0F / textureHeight;
		 Tessellator tessellator = Tessellator.getInstance();
	     VertexBuffer VertexBuffer = tessellator.getBuffer();
	     VertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		 VertexBuffer.pos((double) x, (double) (y + height), z).tex((double) (u * f4), (double) ((v + (float) height) * f5)).endVertex();
		 VertexBuffer.pos((double) (x + width), (double) (y + height), z).tex((double) ((u + (float) width) * f4), (double) ((v + (float) height) * f5)).endVertex();
		 VertexBuffer.pos((double) (x + width), (double) y, z).tex((double) ((u + (float) width) * f4), (double) (v * f5)).endVertex();
		 VertexBuffer.pos((double) x, (double) y, z).tex((double) (u * f4), (double) (v * f5)).endVertex();
		 tessellator.draw();
	}
	
	/**
	 * Draws a Cube with a given Texture
	 * 
	 * @param
	 */
	public static void drawTexturedCube(ResourceLocation location, double x, double y, double z, double width, double height, double depth) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + width / 2, y + height / 2, z + depth / 2);
		GlStateManager.shadeModel(7425);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.disableLighting();
		drawTexture(location, width, height, -width / 2, -height / 2, -depth / 2, width, height, 0, 0);
		GlStateManager.rotate(90, 1.0F, 0, 0F);
		drawTexture(location, width, depth, -width / 2, -depth / 2, -height / 2, width, depth, 0, 0);
		GlStateManager.rotate(180, 1.0F, 0, 0F);
		drawTexture(location, width, depth, -width / 2, -depth / 2, -height / 2, width, depth, 0, 0);
		GlStateManager.rotate(270, 1.0F, 0, 0F);
		drawTexture(location, width, height, -width / 2, -height / 2, -depth / 2, width, height, 0, 0);
		GlStateManager.rotate(90, 0F, 1.0F, 0F);
		drawTexture(location, depth, height, -depth / 2, -height / 2, -width / 2, depth, height, 0, 0);
		GlStateManager.rotate(-90, 0F, 1.0F, 0F);
		GlStateManager.rotate(90, 0F, 1.0F, 0F);
		GlStateManager.rotate(-180, 1.0F, 0F, 0F);
		drawTexture(location, depth, height, -depth / 2, -height / 2, -width / 2, depth, height, 0, 0);
		GlStateManager.enableLighting();
		GlStateManager.shadeModel(7424);
		GlStateManager.popMatrix();
	}
	
	/**
	 * Draws a Block with a given Texture
	 * 
	 * @param
	 */
	public static void drawBlock(ResourceLocation loc, double posX, double posY, double posZ, double d, double e, double f) {
		drawTexturedCube(loc, posX + (0.5 - d / 2), posY + (0.5 - e / 2), posZ + (0.5 - f / 2), d, e, f);
	}
	
	public static void drawTexturePoints(String text, Vec3d vec, Vec3d vec1, Vec3d vec2, Vec3d Vec3d, double u, double v) {
		 Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(text));
		 Tessellator tessellator = Tessellator.getInstance();
		 VertexBuffer VertexBuffer = tessellator.getBuffer();
	     VertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		 VertexBuffer.pos(vec.xCoord, vec.yCoord, vec.zCoord).tex(0, v).endVertex();
		 VertexBuffer.pos(vec1.xCoord, vec1.yCoord, vec1.zCoord).tex(0, 0).endVertex();
		 VertexBuffer.pos(vec2.xCoord, vec2.yCoord, vec2.zCoord).tex(u, 0).endVertex();
		 VertexBuffer.pos(Vec3d.xCoord, Vec3d.yCoord, Vec3d.zCoord).tex(u, v).endVertex();
		 tessellator.draw();
	}
	
	public static void drawSwell(String text, double x, double y, double z) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y, z);
		GlStateManager.enableNormalize();
		RenderHelper.disableStandardItemLighting();
		
		double u = 2, v = 2;
		
		GlStateManager.rotate(-90, 1.0F, 0, 0);
		
		drawTexture(new ResourceLocation(text), 2, 0.4, -1, -0.2, 0, 2, 0.4, 0, 0);
		
		GlStateManager.rotate(180, 1.0F, 0, 0);
		
		drawTexture(new ResourceLocation(text), u, v, -0.8, -0.1, -0.2, 1.6, 0.2, 0, 0);
		
		GlStateManager.rotate(-90, 1.0F, 0, 0);
		
		Vec3d corn13 = new Vec3d(0.8, 0.2, 0.1);
		Vec3d corn23 = new Vec3d(0.8, 0.2, -0.1);
		Vec3d corn33 = new Vec3d(1, 0, 0.2);
		Vec3d corn43 = new Vec3d(1, 0, -0.2);
		drawTexturePoints(text, corn23, corn13, corn33, corn43, u, v);
		
		Vec3d corn1 = new Vec3d(-0.8, 0.2, 0.1);
		Vec3d corn2 = new Vec3d(0.8, 0.2, 0.1);
		Vec3d corn3 = new Vec3d(-1, 0, 0.2);
		Vec3d corn4 = new Vec3d(1, 0, 0.2);
		drawTexturePoints(text, corn2, corn1, corn3, corn4, u, v);
		
		GlStateManager.rotate(180F, 0F, 1.0F, 0F);
		
		Vec3d corn15 = new Vec3d(0.8, 0.2, 0.1);
		Vec3d corn25 = new Vec3d(0.8, 0.2, -0.1);
		Vec3d corn35 = new Vec3d(1, 0, 0.2);
		Vec3d corn45 = new Vec3d(1, 0, -0.2);
		drawTexturePoints(text, corn25, corn15, corn35, corn45, u, v);
		
		Vec3d corn12 = new Vec3d(-0.8, 0.2, 0.1);
		Vec3d corn22 = new Vec3d(0.8, 0.2, 0.1);
		Vec3d corn32 = new Vec3d(-1, 0, 0.2);
		Vec3d corn42 = new Vec3d(1, 0, 0.2);
		drawTexturePoints(text, corn22, corn12, corn32, corn42, u, v);
		
		GlStateManager.popMatrix();
	}
	
	@Deprecated
	public static void drawRailPart(String text, double x, double y, double z) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y, z);
		GlStateManager.enableNormalize();
		RenderHelper.disableStandardItemLighting();
				
		GlStateManager.popMatrix();
	}
	
	public static void drawVertex(String str, Vertex ve, double x, double y, double z) {
		drawTexturePoints(str, ve.getVec1(), ve.getVec2(), ve.getVec3(), ve.getVec4(), 0, 0);
	}
	
	/**
	 * @param imageStream
	 * @return {@link ByteBuffer}
	 * @throws IOException
	 */
	public static ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException {
		BufferedImage bufferedimage = ImageIO.read(imageStream);
		int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[]) null, 0, bufferedimage.getWidth());
		ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
		int[] aint1 = aint;
		int i = aint.length;
		
		for (int j = 0; j < i; ++j) {
			int k = aint1[j];
			bytebuffer.putInt(k << 8 | k >> 24 & 255);
		}
		
		bytebuffer.flip();
		return bytebuffer;
	}
}