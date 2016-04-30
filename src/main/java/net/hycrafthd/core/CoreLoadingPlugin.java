package net.hycrafthd.core;

import java.util.Map;
import java.util.logging.Logger;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@SortingIndex(0)
@TransformerExclusions("net.hycrafthd.core")
@Name(value = "CoreLibaryLoadingPlugin")
public class CoreLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return "net.hycrafthd.core.CoreModContainer";
	}

	@Override
	public String getSetupClass() {
		return "net.hycrafthd.core.CoreModSetup";
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
