package net.hycrafthd.corelib.via;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.corelib.util.RGBA;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
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
					 VertexBuffer worldrenderer = tessellator.getBuffer();
				     worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
				     for(Vec3d vec : ((MegaVertex)ver).getVertex()){
						 worldrenderer.pos(vec.xCoord, vec.yCoord, vec.zCoord).color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getAlpha()).endVertex();
				     }
					 tessellator.draw();
				}
			}
		} catch (Throwable th) {
			CoreLib.getLogger().error("Failer while drawing", th);
		}
	}
}
