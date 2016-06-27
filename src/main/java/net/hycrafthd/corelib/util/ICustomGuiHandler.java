package net.hycrafthd.corelib.util;

import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * @see {@link IGuiHandler}
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public interface ICustomGuiHandler extends IGuiHandler {

	/**
	 * Modid
	 * 
	 * @return modid
	 */
	public String getMod();

}
