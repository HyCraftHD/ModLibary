package net.hycrafthd.core;

import java.io.File;
import java.util.Map;

import net.hycrafthd.core.exeption.UnsupportedVersionExeption;
import net.hycrafthd.core.util.CoreUtil;
import net.hycrafthd.core.util.FileUtil;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.relauncher.IFMLCallHook;

public class CoreModSetup implements IFMLCallHook {

	@Override
	public Void call() throws Exception {
		tryLoadingRightLibaryOrDownloading();
		return null;
	}

	private void tryLoadingRightLibaryOrDownloading() {
		if (!CoreUtil.isSupportedVersion()) {
			throw new UnsupportedVersionExeption("1.8, 1.8.8, 1.8.9, 1.9!");
		}
		File libaryDir = new File(CoreUtil.getMinecraftDir(), "coreLibary");
		if (!libaryDir.exists()) {
			libaryDir.mkdir();
		}
		File libary = new File(libaryDir, CoreUtil.getVersion() + ".jar");
		if (!libary.exists()) {
			CoreUtil.getLogger().info("Trying to download version corefile!");
			FileUtil.downloadFile("http://www.hycrafthd.net/test.jar", libary);
			CoreUtil.getLogger().info("Downloaded file successfully!");
		}
		try {
			@SuppressWarnings("resource")
			ModClassLoader modclasslaoder = new ModClassLoader((LaunchClassLoader) CoreModSetup.class.getClassLoader());
			modclasslaoder.addFile(libary);
			CoreUtil.getLogger().info("Loaded corefile successfully as libary!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

}
