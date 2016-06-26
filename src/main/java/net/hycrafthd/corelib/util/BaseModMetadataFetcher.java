package net.hycrafthd.corelib.util;

import java.io.InputStream;

import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.ModMetadata;

public class BaseModMetadataFetcher {

	private ModMetadata modmeta;

	public BaseModMetadataFetcher(String modid) {
		this("/mcmod.info", modid);
	}

	public BaseModMetadataFetcher(String injarpath, String modid) {
		this(BaseModMetadataFetcher.class.getResourceAsStream(injarpath), modid);
	}

	public BaseModMetadataFetcher(InputStream inputstream, String modid) {
		ModMetadata meta = MetadataCollection.from(inputstream, modid).getMetadataForId(modid, null);
		if (meta != null) {
			modmeta = meta;
		} else {
			modmeta = new ModMetadata();
			modmeta.modId = modid;
		}
	}

	public ModMetadata getModmeta() {
		return modmeta;
	}

}
