package net.hycrafthd.corelib.util.asm;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

/**
 * Asm method matcher
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class MethodMatcher {

	/**
	 * Class name
	 */
	private String clazzName;
	/**
	 * Method description
	 */
	private String methodDescription;
	/**
	 * Method name mcp
	 */
	private String methodMcpName;
	/**
	 * Method name srg
	 */
	private String methodSrgName;

	/**
	 * Constructor
	 * 
	 * @param clazzName
	 * @param methoddescription
	 * @param methodMcpName
	 * @param methodSrgName
	 */
	public MethodMatcher(String clazzName, String methoddescription, String methodMcpName, String methodSrgName) {
		this.clazzName = clazzName;
		this.methodDescription = methoddescription;
		this.methodMcpName = methodMcpName;
		this.methodSrgName = methodSrgName;
	}

	/**
	 * Constructor
	 * 
	 * @param mappedtype
	 * @param methoddescription
	 * @param methodMcpName
	 * @param methodSrgName
	 */
	public MethodMatcher(MappedType mappedtype, String methoddescription, String methodMcpName, String methodSrgName) {
		this(mappedtype.getName(), methoddescription, methodMcpName, methodSrgName);
	}

	/**
	 * Return true if method matches
	 * 
	 * @param methodName
	 * @param methodDescription
	 * @return true or false if not match
	 */
	public boolean match(String methodName, String methodDescription) {
		if (!methodDescription.equals(methodDescription))
			return false;
		if (methodName.equals(methodMcpName))
			return true;
		if (!ASMUtil.useSrgNames())
			return false;
		String mapped = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(clazzName, methodName, methodDescription);
		return mapped.equals(methodSrgName);
	}

	/**
	 * Get string override
	 */
	@Override
	public String toString() {
		return String.format("%s.[%s,%s] %s", clazzName, methodSrgName, methodMcpName, methodDescription);
	}

}