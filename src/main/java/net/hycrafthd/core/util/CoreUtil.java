package net.hycrafthd.core.util;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.ForgeVersion;

public class CoreUtil {

	private static String version;

	private static Logger logger = LogManager.getLogger("CoreLibary");
	
	private static String downloadPath = "https://github.com/HyCraftHD/CoreLibary";

	public static File getMinecraftDir() {
		return new File(System.getProperty("user.dir"));
	}

	public static Logger getLogger() {
		return logger;
	}

	public static String getVersion() {
		return version;
	}

	public static boolean is1_8() {
		return version == "1.8";
	}

	public static boolean is1_8_8() {
		return version == "1.8.8";
	}

	public static boolean is1_8_9() {
		return version == "1.8.9";
	}

	public static boolean is1_9() {
		return version == "1.9";
	}

	public static boolean contains1_8() {
		return is1_8() || is1_8_8() || is1_8_9();
	}

	public static boolean contains1_9() {
		return is1_9();
	}

	public static boolean isSupportedVersion() {
		return contains1_8() || contains1_9();
	}

	public static void invokeMethod(String classname, String method, ClassObject classobject) {
		Class clazz = ReflectionUtil.getClass(classname);
		ReflectionUtil.invokeMethod(clazz, ReflectionUtil.getInstance(clazz), method, classobject);
		logger(clazz, method, classobject);
	}

	private static void logger(Class clazz, String method, ClassObject classobject) {
		System.out.println(clazz.getName() + " -> " + method + "\n------------------------------------\nClassobject\n------------------------------------\n" + classobject.toString());
	}

	static {
		version = ForgeVersion.mcVersion;
	}

}
