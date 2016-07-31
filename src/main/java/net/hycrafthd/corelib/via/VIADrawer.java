package net.hycrafthd.corelib.via;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.corelib.util.RGBA;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VIADrawer {

	private VIAFile fl;

	/**
	 * @author MrTroble
	 * 
	 * @param VIAFile fl - the file to draw
	 */
	
	@SideOnly(Side.CLIENT)
	public VIADrawer(VIAFile fl) {
		OpenGlHelper.initializeTextures();
		this.fl = fl;
	}

	public void drawNormal(String s, double x, double y, double z,RGBA rgb) {
		try {
			for (int i = 0; i < fl.getMaxGroups(); i++) {
				BaseVertex ver = fl.interpretVertex(i);
				if(ver instanceof Vertex){
				LWJGLUtils.drawVertex(s, ((Vertex)ver), x, y, z,rgb);
				}else if(ver instanceof MegaVertex){
					 Tessellator tessellator = Tessellator.getInstance();
					 WorldRenderer worldrenderer = tessellator.getWorldRenderer();
				     worldrenderer.startDrawingQuads();
				     for(Vec3 vec : ((MegaVertex)ver).getVertex()){
				    	 worldrenderer.setColorRGBA(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getAlpha());
						 worldrenderer.addVertex(vec.xCoord, vec.yCoord, vec.zCoord);
				     }
					 tessellator.draw();
				}
			}
		} catch (Throwable th) {
			CoreLib.getLogger().error("Failer while drawing", th);
		}
	}
}
