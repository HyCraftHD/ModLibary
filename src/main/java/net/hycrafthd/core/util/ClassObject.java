package net.hycrafthd.core.util;

import java.util.Arrays;

import com.google.common.collect.Lists;

public class ClassObject {

	private Class[] clazz;
	private Object[] obj;

	public ClassObject(Class[] clazz, Object[] obj) {
		if (clazz.length == obj.length) {
			this.clazz = clazz;
			this.obj = obj;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Class[] getClazz() {
		return clazz;
	}

	public Object[] getObj() {
		return obj;
	}

	public static ClassObject forObj(Object... obj) { // Everyone knows how to make this methode better?
		Class[] classes = new Class[obj.length];
		for (int i = 0; i < classes.length; i++) {
			Class clazz = obj[i].getClass();
			while (!ReflectionUtil.isPrimitiveOrObjectClass(clazz) && clazz.getSuperclass() != null && !ReflectionUtil.isPrimitiveOrObjectClass(clazz.getSuperclass())) {
				System.out.println(clazz.getSuperclass().getName() + " ->" + ReflectionUtil.isPrimitiveOrObjectClass(clazz.getSuperclass()));
				clazz = clazz.getSuperclass();
			}
			classes[i] = clazz;
		}
		return new ClassObject(classes, obj);
	}

	@Override
	public String toString() {
		String classes = "";
		String objects = "";
		for (Class clazz : getClazz()) {
			classes += clazz.getCanonicalName() + "\n";
		}
		for (Object obj : getObj()) {
			objects += obj.toString() + "\n";
		}
		return this.getClass().getName() + "\nClasses\n" + classes + "\nObjects\n" + objects;
	}

}
