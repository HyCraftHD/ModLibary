package net.hycrafthd.corelib.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.corelib.via.Vertex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

@SuppressWarnings("deprecation")
public class LWJGLUtils {

	
	/**
	 * Draws a Horizontal Line<br>
	 *<br>x = x Start Position of Line
	 *<br>x2 = x End Position of Line
	 *<br>y = y Position of Line
	 *<br>r = your Color as {@link RGBA}
	 * 
	 * @param 
	 * 
	 */
	public static void drawHLine(double x, double x2, double y, RGBA r) {
		drawGradientRect(x, y, x2, y + 1, r, r);
	}

	/**
	 * Draws a Vertical Line<br>
	 *<br>x = x Position of Line
	 *<br>y = y Start Position of Line
	 *<br>down = y End Position of Line
	 *<br>r = your Color as {@link RGBA}
	 * 
	 * @param 
	 * 
	 */
	public static void drawVLine(double x, double y, double down, RGBA r) {
		drawGradientRect(x, y, x + 1, down, r, r);
	}
	
	/**
	 * Draws a Horizontal Line with Z Pos<br>
	 *<br>x = x Start Position of Line
	 *<br>x2 = x End Position of Line
	 *<br>y = y Position of Line
	 *<br>r = your Color as {@link RGBA}
	 * <br>z = z Position in 3D Rooms only
	 * @param 
	 */
	public static void drawHLine(double x, double x2, double y, RGBA r,double z) {
		drawGradientRect(x, y, x2, y + 1, r, r,z);
	}

	/**
	 * Draws a Vertical Line with Z Pos<br>
	 *<br>x = x Position of Line
	 *<br>y = y Start Position of Line
	 *<br>down = y End Position of Line
	 *<br>r = your Color as {@link RGBA}
	 * <br>z = z Position in 3D Rooms only
	 * @param 
	 */
	public static void drawVLine(double x, double y, double down, RGBA r,double z) {
		drawGradientRect(x, y, x + 1, down, r, r,z);
	}

	
	/**
	 * Draws a given Region
	 * 
	 * <br>rgb = Color as {@link RGBA}
	 * 
	 * @param 
	 */
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA rgb,double z){
		drawGradientRect(left, top, right, bottom, rgb, rgb,z);
	}
	
	/**
	 * Draws a given Region
	 * 
	 * <br>rgb = Color as {@link RGBA}
	 * 
	 * @param 
	 */
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA rgb){
		drawGradientRect(left, top, right, bottom, rgb, rgb);
	}
	
	/**
	 * Draws a given Region
	 * 
	 * <br>start = Start Color as {@link RGBA}
	 * <br>end = End Color as {@link RGBA}
	 * <br>
	 * <br>Just try out how the Start or End Color
	 * effects your draw
	 * 
	 * @param 
	 */
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA start, RGBA end){
		drawGradientRect(left, top, right, bottom, start, end, 0);
	}
	
	
	/**
	 * Draws a given Region
	 * 
	 * <br>start = Start Color as {@link RGBA}
	 * <br>end = End Color as {@link RGBA}
	 * <br>
	 * <br>Just try out how the Start or End Color
	 * effects your draw
	 * 
	 * @param 
	 */
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA start, RGBA end,double z) {
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.shadeModel(7425);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha());
		worldrenderer.addVertex((double) right, (double) top, (double) z);
		worldrenderer.addVertex((double) left, (double) top, (double) z);
		worldrenderer.setColorRGBA(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha());
		worldrenderer.addVertex((double) left, (double) bottom, (double) z);
		worldrenderer.addVertex((double) right, (double) bottom, (double) z);
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}
	
	/**
	 * Draws a given Region with a Z multiplier<br>
	 * (means that you get a Curved Region)<br>
	 * <br>start = Start Color as {@link RGBA}
	 * <br>end = End Color as {@link RGBA}
	 * <br>
	 * <br>Just try out how the Start or End Color
	 * effects your draw
	 * 
	 * @param 
	 */
	public static void drawGradientRectWithMultiplier(int left, int top, int right, int bottom, RGBA start, RGBA end,double z,double multiplier) {
		double oldZ = z;
		for(int i = 0;i < right;i++){
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
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha());
		worldrenderer.addVertex((double) left + i, (double) top, (double) oldZ);
		worldrenderer.addVertex((double) left + 1 + i, (double) top, (double) z);
		worldrenderer.setColorRGBA(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha());
		worldrenderer.addVertex((double) left + 1 + i, (double) bottom, (double) z);
		worldrenderer.addVertex((double) left + i, (double) bottom, (double) oldZ);
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180F, 0F, 1.0F, 0F);
		if(left > 0){
		GlStateManager.translate(-left, 0, 0);
		}else{
	    GlStateManager.translate(37, 0, 0);
		}
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha());
		worldrenderer.addVertex((double) left + i, (double) top, (double) -oldZ);
		worldrenderer.addVertex((double) left + 1 + i, (double) top, (double) -z);
		worldrenderer.setColorRGBA(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha());
		worldrenderer.addVertex((double) left + 1 + i, (double) bottom, (double) -z);
		worldrenderer.addVertex((double) left + i, (double) bottom, (double) -oldZ);
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
	 * Draws a Frame of a given Region
	 * <br>
	 * <br>rgb = Start Color as {@link RGBA} 
	 * @param 
	 */
	public static void drawFrame(double x ,double y,double width,double height,RGBA rgb){
	      drawFrame(x, y, width, height, rgb, 0);
	}
	
	/**
	 * Draws a Frame of a given Region
	 * <br>
	 * <br>rgb = Start Color as {@link RGBA} 
	 * @param 
	 */
	public static void drawFrame(double x ,double y,double width,double height,RGBA rgb,double z){
		drawHLine(x - 1, x + width + 1, y - 1, rgb,z);
		drawHLine(x - 1, x + width + 1, y + height, rgb,z);
		drawVLine(x - 1, y, y + height, rgb,z);
		drawVLine(x + width, y, y + height, rgb,z);
	}
	
	/**
	 * Draws a String in the World with Background and Frame
	 * <br>
	 * <br>start = Start Color as {@link RGBA} 
	 * <br>end = End Color as {@link RGBA} 
	 * <br>Frame = Frame Color as {@link RGBA} 
	 * <br>color = Integer The Color of String
	 * 
	 * @param 
	 */
	public static void drawStringInWorld(BlockPos pos,double posX,double posY,double posZ,int p_180535_9_,String text,RGBA start,RGBA end,RGBA frame,int color){
		EntityPlayer pl  = Minecraft.getMinecraft().thePlayer;
        BlockPos po = pl.getPosition();
		if (pos.getX() + 10 > po.getX() && pos.getX() - 10 < po.getX() && pos.getY() + 10 > po.getY() && pos.getY() - 10 < po.getY() && pos.getZ() + 10 > po.getZ() && pos.getZ() - 10 < po.getZ())
		{
		FontRenderer fontrenderer = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX + 0.5F, (float)posY + 1.5F, (float)posZ + 0.5F);
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
        drawGradientRect((double)(-j - 1), (double)(-1 + b0), (double)(j + 1), (double)(8 + b0), start, end);
        drawFrame((double)(-j - 1), (double)(-1 + b0), (double)(j2 + 1), (double)(8 + b0), frame);
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
	 * Draws a String in the World
	 * <br>
	 * <br>color = Integer The Color of String
	 * 
	 * @param 
	 */
	public static void drawOnlyStringInWorld(BlockPos pos,double posX,double posY,double posZ,String text,int color){
		EntityPlayer pl  = Minecraft.getMinecraft().thePlayer;
        BlockPos po = pl.getPosition();
		if (pos.getX() + 10 > po.getX() && pos.getX() - 10 < po.getX() && pos.getY() + 10 > po.getY() && pos.getY() - 10 < po.getY() && pos.getZ() + 10 > po.getZ() && pos.getZ() - 10 < po.getZ())
		{
		FontRenderer fontrenderer = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX + 0.5F, (float)posY + 1.5F, (float)posZ + 0.5F);
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
	 * Draws something in the World
	 * <br>
	 * <br>color = Integer The Color of String
	 * <br>run = a Runnable with your stuff to Render
	 * @param 
	 */
	public static void drawSmThInWorld(BlockPos pos,double posX,double posY,double posZ,Runnable run){
		EntityPlayer pl  = Minecraft.getMinecraft().thePlayer;
        BlockPos po = pl.getPosition();
		if (pos.getX() + 10 > po.getX() && pos.getX() - 10 < po.getX() && pos.getY() + 10 > po.getY() && pos.getY() - 10 < po.getY() && pos.getZ() + 10 > po.getZ() && pos.getZ() - 10 < po.getZ())
		{
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX + 0.5F, (float)posY + 1.5F, (float)posZ + 0.5F);
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
	 * Draws a Frame in the World
	 * <br>
	 * <br>frame = Color of Frame in {@link RGBA}
	 * @param 
	 */
	public static void drawFrameInWorld(BlockPos pos,double posX,double posY,double posZ,final double width,final double height,final RGBA frame){
	   drawSmThInWorld(pos, posX, posY, posZ, new Runnable() {
		@Override
		public void run() {
			GlStateManager.shadeModel(7425);
	        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
	        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	        GlStateManager.enableDepth();
	        GlStateManager.color(1, 1, 1);
	        drawFrame((double)(-width/2 - 1), (double)(-1 + 0), (double)(width + 1), (double)(height + 0), frame);
	        GlStateManager.enableDepth();
	        GlStateManager.enableLighting();
			GlStateManager.shadeModel(7424);
		}
	   });
	}
	
	
	/**Draws a Texture
	 * 
	 * @param
	 */
	public static void drawTexture(ResourceLocation location, double textureWidth, double textureHeight, double x, double y,double z, double width, double height, double u, double v){
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
		double f4 = 1.0F / textureWidth;
		double f5 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV((double)x, (double)(y + height), z, (double)(u * f4), (double)((v + (float)height) * f5));
        worldrenderer.addVertexWithUV((double)(x + width), (double)(y + height), z, (double)((u + (float)width) * f4), (double)((v + (float)height) * f5));
        worldrenderer.addVertexWithUV((double)(x + width), (double)y, z, (double)((u + (float)width) * f4), (double)(v * f5));
        worldrenderer.addVertexWithUV((double)x, (double)y, z, (double)(u * f4), (double)(v * f5));
        tessellator.draw();
	}
	
	/**Draws a Cube with a given Texture
	 * 
	 * @param
	 */
	public static void drawTexturedCube(ResourceLocation location, double x, double y,double z, double width, double height,double depth){
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + width/2, y + height/2, z + depth/2);
		GlStateManager.shadeModel(7425);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableLighting();
		drawTexture(location, width, height, -width/2, -height/2, -depth/2, width, height, 0, 0);
		GlStateManager.rotate(90, 1.0F, 0, 0F);
		drawTexture(location, width, depth, -width/2,-depth/2,-height/2,  width, depth, 0, 0);
		GlStateManager.rotate(180, 1.0F, 0, 0F);
		drawTexture(location, width, depth, -width/2,-depth/2,-height/2,  width, depth, 0, 0);
		GlStateManager.rotate(270, 1.0F, 0, 0F);
	    drawTexture(location, width, height, -width/2, -height/2, -depth/2, width, height, 0, 0);
		GlStateManager.rotate(90, 0F, 1.0F, 0F);
		drawTexture(location, depth, height, -depth/2,-height/2,-width/2, depth, height, 0, 0);
		GlStateManager.rotate(-90, 0F, 1.0F, 0F);
		GlStateManager.rotate(90, 0F, 1.0F, 0F);
		GlStateManager.rotate(-180, 1.0F, 0F, 0F);
		drawTexture(location, depth, height, -depth/2,-height/2,-width/2, depth, height, 0, 0);
		GlStateManager.enableLighting();
		GlStateManager.shadeModel(7424);
		GlStateManager.popMatrix();
	}
	
	/**Draws a Block with a given Texture
	 * 
	 * @param
	 */
	public static void drawBlock(ResourceLocation loc,double posX ,double posY, double posZ,double d,double e,double f){
		drawTexturedCube(loc, posX + (0.5 - d/2), posY + (0.5 - e/2), posZ + (0.5 - f/2), d, e, f);
	}
	
	/**Draws a Block as Conduit
	 * 
	 * @param
	 */
	public static void renderBlockConduit(ItemStack stack, double x, double y,double z)
    {
        IBakedModel ibakedmodel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        ibakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.HEAD);
        renderItem(stack, ibakedmodel,x,y,z);
        GlStateManager.disableAlpha();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
    }
    
	public static void renderItem(ItemStack stack, IBakedModel model,double x,double y,double z)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x , y, z);
       
            renderModel(model, -1,stack);

        GlStateManager.popMatrix();
    }
    
	private static void renderModel(IBakedModel model, int color, ItemStack stack)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.setVertexFormat(DefaultVertexFormats.ITEM);
        EnumFacing[] aenumfacing = EnumFacing.values();
        int j = aenumfacing.length;

        for (int k = 0; k < j; ++k)
        {
            EnumFacing enumfacing = aenumfacing[k];
            renderQuads(worldrenderer, model.getFaceQuads(enumfacing), color, stack);
        }

        renderQuads(worldrenderer, model.getGeneralQuads(), color, stack);
        tessellator.draw();
    }
    
    @SuppressWarnings("rawtypes")
	private static void renderQuads(WorldRenderer renderer, List quads, int color, ItemStack stack)
    {
        boolean flag = color == -1 && stack != null;
        BakedQuad bakedquad;
        int j;

        for (Iterator iterator = quads.iterator(); iterator.hasNext(); renderQuad(renderer, bakedquad, j))
        {
            bakedquad = (BakedQuad)iterator.next();
            j = color;

            if (flag && bakedquad.hasTintIndex())
            {
                j = stack.getItem().getColorFromItemStack(stack, bakedquad.getTintIndex());

                if (EntityRenderer.anaglyphEnable)
                {
                    j = TextureUtil.anaglyphColor(j);
                }

                j |= -16777216;
            }
        }
    }
    
    private static void renderQuad(WorldRenderer renderer, BakedQuad quad, int color)
    {
        renderer.addVertexData(quad.getVertexData());
        if(quad instanceof net.minecraftforge.client.model.IColoredBakedQuad)
            net.minecraftforge.client.ForgeHooksClient.putQuadColor(renderer, quad, color);
        else
        renderer.putColor4(color);
        putQuadNormal(renderer, quad);
    }

    private static void putQuadNormal(WorldRenderer renderer, BakedQuad quad)
    {
        Vec3i vec3i = quad.getFace().getDirectionVec();
        renderer.putNormal((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
    }
    
    public static void drawTexturePoints(String text,Vec3 vec,Vec3 vec1,Vec3 vec2,Vec3 vec3,double u,double v){
    	Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(text));
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV( vec.xCoord, vec.yCoord, vec.zCoord, 0,v);
        worldrenderer.addVertexWithUV( vec1.xCoord, vec1.yCoord, vec1.zCoord, 0,0);
        worldrenderer.addVertexWithUV( vec2.xCoord, vec2.yCoord, vec2.zCoord, u,0);
        worldrenderer.addVertexWithUV( vec3.xCoord, vec3.yCoord, vec3.zCoord, u,v);
        tessellator.draw();
    }

	public static void drawSwell(String text,double x,double y,double z){
    	GlStateManager.pushMatrix();
    	GlStateManager.translate(x + 0.5, y, z);
    	GlStateManager.enableNormalize();
    	RenderHelper.disableStandardItemLighting();
    	
    	double u = 2,v = 2;
    	
    	GlStateManager.rotate(-90, 1.0F, 0, 0);
    	
    	drawTexture(new ResourceLocation(text), 2, 0.4, -1, -0.2, 0, 2, 0.4, 0, 0);
    	
    	GlStateManager.rotate(180, 1.0F, 0, 0);
    	
    	drawTexture(new ResourceLocation(text), u, v, -0.8, -0.1, -0.2, 1.6, 0.2, 0, 0);
    	
    	GlStateManager.rotate(-90, 1.0F, 0, 0);
    	    	
    	Vec3 corn13 = new Vec3(0.8, 0.2, 0.1);
    	Vec3 corn23 = new Vec3(0.8, 0.2, -0.1);
    	Vec3 corn33 = new Vec3(1, 0, 0.2);
    	Vec3 corn43 = new Vec3(1, 0, -0.2);
		drawTexturePoints(text, corn23,corn13, corn33, corn43,u,v);
    	
    	Vec3 corn1 = new Vec3(-0.8, 0.2, 0.1);
    	Vec3 corn2 = new Vec3(0.8, 0.2, 0.1);
    	Vec3 corn3 = new Vec3(-1, 0, 0.2);
    	Vec3 corn4 = new Vec3(1, 0, 0.2);
    	drawTexturePoints(text,corn2,corn1, corn3, corn4,u,v);
    	
    	GlStateManager.rotate(180F, 0F, 1.0F, 0F);
    	
    	Vec3 corn15 = new Vec3(0.8, 0.2, 0.1);
    	Vec3 corn25 = new Vec3(0.8, 0.2, -0.1);
    	Vec3 corn35 = new Vec3(1, 0, 0.2);
    	Vec3 corn45 = new Vec3(1, 0, -0.2);
		drawTexturePoints(text, corn25,corn15, corn35, corn45,u,v);
    	
    	Vec3 corn12 = new Vec3(-0.8, 0.2, 0.1);
    	Vec3 corn22 = new Vec3(0.8, 0.2,0.1);
    	Vec3 corn32 = new Vec3(-1, 0, 0.2);
    	Vec3 corn42 = new Vec3(1, 0, 0.2);
    	drawTexturePoints(text, corn22,corn12, corn32, corn42,u,v);
    	    	
    	GlStateManager.popMatrix();
    }
	
	public static void drawRailPart(String text,double x,double y,double z){
    	GlStateManager.pushMatrix();
    	GlStateManager.translate(x + 0.5, y, z);
    	GlStateManager.enableNormalize();
    	RenderHelper.disableStandardItemLighting();
    	
        GlStateManager.popMatrix();
	}
	
	public static void drawVertex(String str,Vertex ve,double x,double y,double z,RGBA rgb){
		Vec3 vec = ve.getVec1();
    	Vec3 vec1 = ve.getVec2();
    	Vec3 vec2 = ve.getVec3();
    	Vec3 vec3 = ve.getVec4();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getAlpha());
        worldrenderer.addVertex( vec.xCoord, vec.yCoord, vec.zCoord);
        worldrenderer.addVertex( vec1.xCoord, vec1.yCoord, vec1.zCoord);
        worldrenderer.addVertex( vec2.xCoord, vec2.yCoord, vec2.zCoord);
        worldrenderer.addVertex( vec3.xCoord, vec3.yCoord, vec3.zCoord);
        tessellator.draw();
	}
	
	
	/**
	 * @param imageStream
	 * @return {@link ByteBuffer}
	 * @throws IOException
	 */
	public static ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException
    {
        BufferedImage bufferedimage = ImageIO.read(imageStream);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
        int[] aint1 = aint;
        int i = aint.length;

        for (int j = 0; j < i; ++j)
        {
            int k = aint1[j];
            bytebuffer.putInt(k << 8 | k >> 24 & 255);
        }

        bytebuffer.flip();
        return bytebuffer;
    }
}
