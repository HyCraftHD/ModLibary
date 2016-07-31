package net.hycrafthd.corelib.util;

import java.io.InputStream;
import java.lang.reflect.Field;

import net.hycrafthd.corelib.CoreLib;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Read a custom mcmod.info file
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class BaseModMetadataFetcher {

	/**
	 * Readed modmeta
	 */
	private ModMetadata modmeta;

	/**
	 * Constructor for default mcmod.info file
	 * 
	 * @param modid
	 *            modid
	 */
	public BaseModMetadataFetcher(String modid) {
		this("/mcmod.info", modid);
	}

	/**
	 * Constructor for special injar info file
	 * 
	 * @param injarpath
	 *            Injar path like /mcmod.info
	 * @param modid
	 *            modid
	 */
	public BaseModMetadataFetcher(String injarpath, String modid) {
		this(BaseModMetadataFetcher.class.getResourceAsStream(injarpath), modid);
	}

	/**
	 * Constructor for special info inputstream
	 * 
	 * @param inputstream
	 *            Inputstream to read modmetadata
	 * @param modid
	 *            modid
	 */
	public BaseModMetadataFetcher(InputStream inputstream, String modid) {
		ModMetadata meta = MetadataCollection.from(inputstream, modid).getMetadataForId(modid, null);
		if (meta != null) {
			modmeta = meta;
		} else {
			modmeta = new ModMetadata();
			modmeta.modId = modid;
		}
	}

	/**
	 * Getter for modmeta
	 * 
	 * @return readed modmeta
	 */
	public ModMetadata getModmeta() {
		return modmeta;
	}

	/**
	 * Applies new Modmeta to old metadata (e.g in preinit {@link FMLInitializationEvent}.getModmeta())
	 * 
	 * @param modmetatoapply
	 *            modmeta to apply new
	 */
	public void applyMetadata(ModMetadata modmetatoapply) {
		try {
			Class<ModMetadata> clazz = ModMetadata.class;
			for (Field field : clazz.getDeclaredFields()) {
				field.set(modmetatoapply, field.get(getModmeta()));
			}
		} catch (Throwable th) {
			CoreLib.getLogger().error("Error while appling new modmeta!", th);
		}
	}

}
