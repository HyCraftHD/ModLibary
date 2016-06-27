package net.hycrafthd.corelib.util.asm;

import net.hycrafthd.corelib.CoreLib;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

/**
 * Util class for asm
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class ASMUtil {

	/**
	 * Is deobfuscated environment
	 * 
	 * @return useSrg
	 */
	public static boolean useSrgNames() {
		Boolean deobfuscated = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		return deobfuscated == null || !deobfuscated;
	}

	/**
	 * Get right maptype
	 * 
	 * @param clsName
	 *            classname
	 * @return maptype
	 */
	public static String getMappedName(String clsName) {
		return useSrgNames() ? FMLDeobfuscatingRemapper.INSTANCE.unmap(clsName) : clsName;
	}

	/**
	 * Asm logger
	 * 
	 * @param type
	 *            AsmLogType
	 * @param obj
	 *            Objects
	 */
	public static void asmLogger(ASMLogType type, Object... obj) {
		try {
			String message = "";
			switch (type) {
			case TRYING:
				message = String.format("Trying to patch class: %s", obj[0]);
				break;
			case MATCHING:
				message = String.format("Method match: %s", obj[0]);
				break;
			case INJECTING:
				message = String.format("Injecting hook %s", obj[0]);
				break;
			case INJECTED:
				message = "Injected!";
				break;
			}
			CoreLib.getLogger().info(message);
		} catch (Throwable th) {
		}
	}

}
