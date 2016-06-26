package net.hycrafthd.corelib.core;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.logger.AbstractLogger;

/**
 * Logger for CoreLib
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class CoreLibLogger extends AbstractLogger {

	/**
	 * Constructor
	 */
	public CoreLibLogger() {
		super(CoreLib.name);
	}

}
