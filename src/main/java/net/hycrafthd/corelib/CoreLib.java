package net.hycrafthd.corelib;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class CoreLib extends DummyModContainer {

	public static final String MODID = "corelib";
	public static final String NAME = "Core Libary";

	public static final String MCVERSION = "1.8";
	public static final String VERSION = "0.1";

	public static final ArrayList<String> AUTHORS = new ArrayList<String>();

	static {
		AUTHORS.add("HyCraftHD");
	}

	private static CoreLib instance;

	private Logger logger;

	public CoreLib() {
		super(new ModMetadata());

		instance = this;

		logger = LogManager.getLogger(NAME);

		ModMetadata meta = getMetadata();
		meta.modId = MODID;
		meta.name = NAME;
		meta.version = VERSION;
		meta.authorList = AUTHORS;
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	// @Subscribe
	// public void init(FMLInitializationEvent event) {
	// }

	public Logger getLogger() {
		return logger;
	}

	public static CoreLib getInstance() {
		return instance;
	}

}
