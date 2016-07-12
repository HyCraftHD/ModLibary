package net.hycrafthd.corelib;

import java.io.File;
import java.net.URL;
import java.util.*;

import com.google.common.eventbus.*;

import net.hycrafthd.corelib.core.*;
import net.hycrafthd.corelib.registry.*;
import net.hycrafthd.corelib.util.McVersionCompare;
import net.hycrafthd.corelib.util.event.CoreEventBus;
import net.hycrafthd.corelib.util.gen.OreGen;
import net.hycrafthd.corelib.util.process.ProcessHandler;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

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
	public static final String mcversion = "[1.10]";
	/**
	 * Current version of CoreLib
	 */
	public static final String version = "0.5-alpha";
	
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
		
		// Allow access to cloudflare protected urls
		System.setProperty("http.agent", "Chrome");
		
		McVersionCompare versioncompare = new McVersionCompare(mcversion);
		
		if (!versioncompare.containsVersion(ForgeVersion.mcVersion)) {
			CrashReport crash = CrashReport.makeCrashReport(new WrongMinecraftVersionException(this, ForgeVersion.mcVersion), "Mcversion is not supported! Allowed: " + mcversion);
			CoreLib.getLogger().error(crash.getCompleteReport());
			FMLCommonHandler.instance().exitJava(0, true);
		}
		
		instance = this;
	}
	
	@Override
	public URL getUpdateUrl() {
		try {
			// TODO own updater
			return new URL("https://www.hycrafthd.net/mods/corelib/update.json");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
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
		if (event.getSide() == Side.CLIENT) {
			EventRegistry.register(new UpdaterInformation());
		}
	}
	
	/**
	 * Serverstarting event
	 */
	@Subscribe
	public static void serverstarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandCschematic());
	}
	
	/**
	 * Fix this to non null //TODO
	 */
	@Override
	public File getSource() {
		return null;
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
