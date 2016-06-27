package net.hycrafthd.umod.VIA;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.LWJGLUtils;
import net.minecraft.client.renderer.OpenGlHelper;
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

	public void drawNormal(String s, double x, double y, double z) {
		try {
			for (int i = 0; i < fl.getMaxGroups(); i++) {
				LWJGLUtils.drawVertex(s, fl.interpretVertex(i), x, y, z);
			}
		} catch (Throwable th) {
			CoreLib.getLogger().error("Failer while drawing", th);
		}
	}
}
