package net.hycrafthd.umod.VIA;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.LWJGLUtils;

public class VIADrawer {

	private VIAFile fl;

	public VIADrawer(VIAFile fl) {
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
