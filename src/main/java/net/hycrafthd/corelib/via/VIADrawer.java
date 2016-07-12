package net.hycrafthd.corelib.via;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.NotWorking;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.*;

public class VIADrawer {
	
	private VIAFile fl;
	
	/**
	 * @author MrTroble
	 * 
	 * @param VIAFile
	 *            fl - the file to draw
	 */
	
	@SideOnly(Side.CLIENT)
	public VIADrawer(VIAFile fl) {
		OpenGlHelper.initializeTextures();
		this.fl = fl;
	}
	
	@NotWorking
	public void drawNormal(String s, double x, double y, double z) {
		try {
			for (int i = 0; i < fl.getMaxGroups(); i++) {
				// TODO
				// LWJGLUtils.drawVertex(s, fl.interpretVertex(i), x, y, z);
			}
		} catch (Throwable th) {
			CoreLib.getLogger().error("Failer while drawing", th);
		}
	}
}
