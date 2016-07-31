package net.hycrafthd.corelib.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstract logger implemention
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public abstract class AbstractLogger {

	/**
	 * Apache logger
	 */
	private Logger logger;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            Name of the logger
	 */
	public AbstractLogger(String name) {
		logger = LogManager.getLogger(name);
	}

	/**
	 * Log
	 * 
	 * @param level
	 * @param msg
	 */
	public void log(Level level, String msg) {
		logger.log(level, msg);
	}

	/**
	 * Info
	 * 
	 * @param msg
	 */
	public void info(String msg) {
		logger.info(msg);
	}

	/**
	 * Warn
	 * 
	 * @param msg
	 */
	public void warn(String msg) {
		logger.warn(msg);
	}

	/**
	 * Error
	 * 
	 * @param msg
	 */
	public void error(String msg) {
		logger.error(msg);
	}

	/**
	 * Error
	 * 
	 * @param msg
	 * @param th
	 */
	public void error(String msg, Throwable th) {
		logger.error(msg, th);
	}

	/**
	 * Getter for logger
	 * 
	 * @return Apache logger
	 */
	public Logger getLogger() {
		return logger;
	}

}
