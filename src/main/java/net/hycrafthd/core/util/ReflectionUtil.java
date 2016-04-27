package net.hycrafthd.core.util;

import java.lang.reflect.Method;

import net.hycrafthd.core.exeption.UnsupportedVersionExeption;
import net.minecraft.launchwrapper.Launch;

public class ReflectionUtil {

	public static boolean useSrgNames() {
		Boolean deobfuscated = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		return deobfuscated == null || !deobfuscated;
	}

	public static String getMainPackage() {
		String pack = "net.hycrafthd.core";
		if (CoreUtil.contains1_8()) {
			return pack + ".v1_8";
		} else if (CoreUtil.contains1_9()) {
			return pack + ".v1_9";
		} else {
			throw new UnsupportedVersionExeption();
		}
	}

	public static Class getClass(String name) {
		try {
			return Class.forName(getMainPackage() + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T getInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void invokeMethod(Class clazz, Object instance, String methodname, ClassObject classobject) {
		try {
			Method method = clazz.getMethod(methodname, classobject.getClazz());
			method.invoke(instance, classobject.getObj());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isPrimitiveOrObjectClass(Class clazz) {
		if (clazz == Object.class || clazz == Boolean.class || clazz == Character.class || clazz == Byte.class || clazz == Short.class || clazz == Integer.class || clazz == Long.class || clazz == Float.class || clazz == Double.class) {
			return true;
		} else {
			return false;
		}
	}

}
