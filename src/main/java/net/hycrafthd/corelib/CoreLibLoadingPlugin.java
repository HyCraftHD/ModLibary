package net.hycrafthd.corelib;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@SortingIndex(0)
@MCVersion(CoreLib.MCVERSION)
@TransformerExclusions("net.hycrafthd.corelib")
@Name(value = "CoreLibLoadingPlugin")
public class CoreLibLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	public String getModContainerClass() {
		return CoreLib.class.getName();
	}

	public void injectData(Map<String, Object> data) {
	}

	public String getAccessTransformerClass() {
		return null;
	}

}
