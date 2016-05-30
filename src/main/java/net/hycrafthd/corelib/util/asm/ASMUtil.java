package net.hycrafthd.corelib.util.asm;

import java.lang.reflect.Method;

import net.hycrafthd.corelib.CoreLibLogger;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class ASMUtil {

	public static boolean useSrgNames() {
		Boolean deobfuscated = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		return deobfuscated == null || !deobfuscated;
	}

	public static String getMappedName(String clsName) {
		return useSrgNames() ? FMLDeobfuscatingRemapper.INSTANCE.unmap(clsName) : clsName;
	}

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
			CoreLibLogger.info(message);
		} catch (Exception ex) {
		}
	}

}
