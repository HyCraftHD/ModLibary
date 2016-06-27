package net.hycrafthd.corelib;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.hycrafthd.corelib.core.CommandCschematic;
import net.hycrafthd.corelib.core.CoreLibLogger;
import net.hycrafthd.corelib.core.ModMetadataFetcherCoreLib;
import net.hycrafthd.corelib.core.WorldGeneratorCoreLib;
import net.hycrafthd.corelib.registry.EventRegistry;
import net.hycrafthd.corelib.registry.GenerationRegistry;
import net.hycrafthd.corelib.util.VersionCompare;
import net.hycrafthd.corelib.util.event.CoreEventBus;
import net.hycrafthd.corelib.util.gen.OreGen;
import net.hycrafthd.corelib.util.process.ProcessHandler;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.WrongMinecraftVersionException;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * CoreLib mod class
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class CoreLib extends DummyModContainer {

	/**
	 * Modid of CoreLib
	 */
	public static final String modid = "corelib";
	/**
	 * Name of CoreLib
	 */
	public static final String name = "Core Libary";
	/**
	 * Allowed Minecraft versions
	 */
	public static final String mcversion = "[1.8,1.8.8,1.8.9]";
	/**
	 * Current version of CoreLib
	 */
	public static final String version = "0.1";

	/**
	 * CoreLib instance
	 */
	private static CoreLib instance;
	/**
	 * CoreLib logger
	 */
	private static CoreLibLogger logger = new CoreLibLogger();
	/**
	 * GenerationList TODO Add CSchematic support
	 */
	private HashMap<Integer, ArrayList<OreGen>> generationList = new HashMap<Integer, ArrayList<OreGen>>();
	/**
	 * CoreLib eventbus
	 */
	private CoreEventBus bus = new CoreEventBus();

	/**
	 * Constructor
	 */
	public CoreLib() {
		super(new ModMetadataFetcherCoreLib().getModmeta());

		VersionCompare versioncompare = new VersionCompare(mcversion);

		if (!versioncompare.containsVersion(ForgeVersion.mcVersion)) {
			CrashReport crash = CrashReport.makeCrashReport(new WrongMinecraftVersionException(this), "Mcversion is not supported! Allowed: " + mcversion);
			CoreLib.getLogger().error(crash.getCompleteReport());
			FMLCommonHandler.instance().exitJava(0, true);
		}
		instance = this;
	}

	/**
	 * Register this class for {@link EventBus}
	 */
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	/**
	 * Postinit event
	 */
	@Subscribe
	public void postinit(FMLPostInitializationEvent event) {
		GenerationRegistry.registerWorldGenerator(new WorldGeneratorCoreLib(), 0);
		EventRegistry.register(new ProcessHandler());
	}
	
	/**
	 * Serverstarting event
	 */
	@Subscribe
	public void serverstarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandCschematic());
	}

	/**
	 * Getter for generationList
	 * 
	 * @return generationList
	 */
	public HashMap<Integer, ArrayList<OreGen>> getGenerationList() {
		return generationList;
	}

	/**
	 * Getter for CoreLib eventbus
	 * 
	 * @return bus
	 */
	public CoreEventBus getEventBus() {
		return bus;
	}

	/**
	 * Get the current instance
	 * 
	 * @return instance of corelib mod
	 */
	public static CoreLib getInstance() {
		return instance;
	}

	/**
	 * Get the logger of corelib
	 * 
	 * @return logger
	 */
	public static CoreLibLogger getLogger() {
		return logger;
	}
	
	

}
