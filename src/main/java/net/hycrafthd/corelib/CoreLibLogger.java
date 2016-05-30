package net.hycrafthd.corelib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoreLibLogger {

	private static Logger logger = LogManager.getLogger(CoreLib.NAME);

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void warm(String msg) {
		logger.warn(msg);
	}

	public static void error(String msg) {
		logger.error(msg);
	}

	public static void error(String msg, Throwable th) {
		logger.error(msg, th);
	}

	public static Logger getLogger() {
		return logger;
	}

}
