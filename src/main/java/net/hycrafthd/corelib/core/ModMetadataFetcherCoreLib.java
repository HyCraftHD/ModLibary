package net.hycrafthd.corelib.core;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.BaseModMetadataFetcher;
import net.minecraftforge.fml.common.ModMetadata;

public class ModMetadataFetcherCoreLib extends BaseModMetadataFetcher {

	public ModMetadataFetcherCoreLib() {
		super("/corelib.info", CoreLib.modid);
	}

	@Override
	public ModMetadata getModmeta() {
		ModMetadata modmeta = super.getModmeta();
		modmeta.name = CoreLib.name;
		modmeta.version = CoreLib.version;
		return modmeta;
	}

}
