package net.hycrafthd.corelib.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractLogger {

	private Logger logger;

	public AbstractLogger(String name) {
		logger = LogManager.getLogger(name);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void warm(String msg) {
		logger.warn(msg);
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String msg, Throwable th) {
		logger.error(msg, th);
	}

	public Logger getLogger() {
		return logger;
	}

}
