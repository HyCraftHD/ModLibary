package net.hycrafthd.corelib;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.hycrafthd.corelib.core.CommandCSchematic;
import net.hycrafthd.corelib.core.ModMetadataFetcherCoreLib;
import net.hycrafthd.corelib.core.WorldGeneratorCoreLib;
import net.hycrafthd.corelib.registry.EventRegistry;
import net.hycrafthd.corelib.registry.GenerationRegistry;
import net.hycrafthd.corelib.util.event.CoreEventBus;
import net.hycrafthd.corelib.util.gen.OreGen;
import net.hycrafthd.corelib.util.process.ProcessHandler;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CoreLib extends DummyModContainer {

	public static final String MODID = "corelib";
	public static final String NAME = "Core Libary";

	public static final String MCVERSION = "1.8";
	public static final String VERSION = "0.1";

	private static CoreLib instance;

	private HashMap<Integer, ArrayList<OreGen>> generationList = new HashMap<Integer, ArrayList<OreGen>>();

	private CoreEventBus bus = new CoreEventBus();

	public CoreLib() {
		super(new ModMetadataFetcherCoreLib().getModmeta());
		instance = this;
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Subscribe
	public void postinit(FMLPostInitializationEvent event) {
		GenerationRegistry.registerWorldGenerator(new WorldGeneratorCoreLib(), 0);
		EventRegistry.register(new ProcessHandler());
	}

	@Subscribe
	public void serverstarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandCSchematic());
	}

	public HashMap<Integer, ArrayList<OreGen>> getGenerationList() {
		return generationList;
	}

	public CoreEventBus getEventBus() {
		return bus;
	}

	public static CoreLib getInstance() {
		return instance;
	}

}
