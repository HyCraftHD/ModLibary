package net.hycrafthd.corelib;

import java.util.Map;

import net.hycrafthd.corelib.asm.CoreLibASMTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.*;

/**
 * Startup loading class
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SortingIndex(0)
@TransformerExclusions("net.hycrafthd.corelib")
@Name(value = "CoreLibLoadingPlugin")
public class CoreLibLoadingPlugin implements IFMLLoadingPlugin {

	/**
	 * Set {@link CoreLibASMTransformer} as ASMTransformer
	 */
	public String[] getASMTransformerClass() {
		return new String[] { CoreLibASMTransformer.class.getName() };
	}

	/**
	 * Set {@link CoreLib} as ModContainer
	 */
	public String getModContainerClass() {
		return CoreLib.class.getName();
	}

	/**
	 * Unused
	 */
	public void injectData(Map<String, Object> data) {
	}

	/**
	 * Unused
	 */
	public String getAccessTransformerClass() {
		return null;
	}

	/**
	 * Unused
	 */
	public String getSetupClass() {
		return null;
	}

}
