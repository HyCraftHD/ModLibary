package net.hycrafthd.corelib.util.asm;

import org.objectweb.asm.Type;

/**
 * Mapped type from object
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class MappedType {

	/**
	 * Class name
	 */
	private String clazzName;

	/**
	 * From class name
	 * 
	 * @param clazzName
	 * @return MappedType
	 */
	public static MappedType from(String clazzName) {
		return new MappedType(clazzName);
	}

	/**
	 * From class
	 * 
	 * @param clazz
	 * @return MappedType
	 */
	public static MappedType from(Class<?> clazz) {
		return new MappedType(clazz.getName().replace('.', '/'));
	}

	/**
	 * Constructor (private)
	 * 
	 * @param clsName
	 *            clazzname
	 */
	private MappedType(String clsName) {
		this.clazzName = ASMUtil.getMappedName(clsName);
	}

	/**
	 * Getter for clazzName
	 * 
	 * @return clazzName
	 */
	public String getName() {
		return clazzName;
	}

	/**
	 * Get type
	 * 
	 * @return Type
	 */
	public Type getType() {
		return Type.getObjectType(clazzName);
	}

}
