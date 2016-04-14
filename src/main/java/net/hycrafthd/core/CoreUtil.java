package net.hycrafthd.core;

import net.hycrafthd.core.exeption.UnsupportedVersionExeption;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoreUtil {

	public static String version;
	public static GameRegistry gameregistry;

	public static Class gameregistryclass;

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

	public static boolean isUnsupportedVersion() {
		boolean supportedversion = isSupportedVersion();
		if (!supportedversion) {
			throw new UnsupportedVersionExeption();
		}
		return false;
	}

	static {
		version = ForgeVersion.mcVersion;
		gameregistry = new GameRegistry();
		gameregistryclass = gameregistry.getClass();
	}

}
