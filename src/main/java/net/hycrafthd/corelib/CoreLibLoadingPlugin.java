package net.hycrafthd.corelib;

import java.util.Map;

import net.hycrafthd.corelib.asm.CoreLibASMTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@SortingIndex(0)
@TransformerExclusions("net.hycrafthd.corelib")
@Name(value = "CoreLibLoadingPlugin")
public class CoreLibLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { CoreLibASMTransformer.class.getName() };
	}

	public String getModContainerClass() {
		return CoreLib.class.getName();
	}

	public void injectData(Map<String, Object> data) {
	}

	public String getAccessTransformerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

}
