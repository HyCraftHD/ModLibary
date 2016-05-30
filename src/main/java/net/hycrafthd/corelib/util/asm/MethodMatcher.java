package net.hycrafthd.corelib.util.asm;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class MethodMatcher {

	private String clazzName;
	private String methodDescription;
	private String methodMcpName;
	private String methodSrgName;

	public MethodMatcher(String clazzName, String methoddescription, String methodMcpName, String methodSrgName) {
		this.clazzName = clazzName;
		this.methodDescription = methoddescription;
		this.methodMcpName = methodMcpName;
		this.methodSrgName = methodSrgName;
	}

	public MethodMatcher(MappedType mappedtype, String methoddescription, String methodMcpName, String methodSrgName) {
		this(mappedtype.getName(), methoddescription, methodMcpName, methodSrgName);
	}

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

	@Override
	public String toString() {
		return String.format("%s.[%s,%s] %s", clazzName, methodSrgName, methodMcpName, methodDescription);
	}

}