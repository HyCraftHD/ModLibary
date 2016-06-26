package net.hycrafthd.corelib.util.asm;

import org.objectweb.asm.Type;

public class MappedType {

	private String clazzName;

	public static MappedType from(String clazzName) {
		return new MappedType(clazzName);
	}

	public static MappedType from(Class<?> clazz) {
		return new MappedType(clazz.getName().replace('.', '/'));
	}

	private MappedType(String clsName) {
		this.clazzName = ASMUtil.getMappedName(clsName);
	}

	public String getName() {
		return clazzName;
	}

	public Type getType() {
		return Type.getObjectType(clazzName);
	}

}
