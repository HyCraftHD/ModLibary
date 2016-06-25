package net.hycrafthd.corelib;

import org.apache.logging.log4j.Level;

import net.hycrafthd.corelib.logger.AbstractLogger;

public class CoreLibLogger extends AbstractLogger {

	public CoreLibLogger() {
		super(CoreLib.NAME);
	}

	public static void log(Level loglvl, String msg) {
		CoreLib.getInstance().getLogger().getLogger().log(loglvl, msg);
	}

}
