package net.hycrafthd.corelib.util;

import java.io.File;

/**
 * Util methods for Files
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class FileUtil {

	/**
	 * Get the directory where the jar is executed
	 * 
	 * @return Directory file
	 */
	public static File getMainDirectory() {
		return new File(System.getProperty("user.dir"));
	}

}
