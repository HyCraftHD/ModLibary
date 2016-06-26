package net.hycrafthd.corelib.core;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.BaseModMetadataFetcher;
import net.minecraftforge.fml.common.ModMetadata;

/**
 * Modmetadatafetcher for CoreLib
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class ModMetadataFetcherCoreLib extends BaseModMetadataFetcher {

	/**
	 * Constructor
	 */
	public ModMetadataFetcherCoreLib() {
		super("/corelib.info", CoreLib.modid);
	}

	/**
	 * Return readed {@link ModMetadata}
	 */
	@Override
	public ModMetadata getModmeta() {
		ModMetadata modmeta = super.getModmeta();
		modmeta.name = CoreLib.name;
		modmeta.version = CoreLib.version;
		return modmeta;
	}

}
