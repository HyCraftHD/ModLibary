package net.hycrafthd.core;

import java.util.Arrays;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.ModMetadata;

public class CoreModContainer extends DummyModContainer {

	public CoreModContainer() {
		super(new ModMetadata());
		ModMetadata modmeta = getMetadata();
		modmeta.modId = "CoreLibaryLoadingPlugin";
	}

}
