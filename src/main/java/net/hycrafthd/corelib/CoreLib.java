package net.hycrafthd.corelib;

import java.io.*;
import java.util.*;

import com.google.common.eventbus.*;

import net.hycrafthd.corelib.analytics.*;
import net.hycrafthd.corelib.core.*;
import net.hycrafthd.corelib.registry.*;
import net.hycrafthd.corelib.util.*;
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
public class CoreLib extends DummyModContainer implements IPlayerAnalytics {
	
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
	public static final String mcversion = "[1.8]";
	/**
	 * Current version of CoreLib
	 */
	public static final String version = "0.6-alpha";
	
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
	 * Analytics
	 */
	private Analytics analytics;
	/**
	 * Configuration
	 */
	private File configuration;
	
	/**
	 * Constructor
	 */
	public CoreLib() {
		super(new ModMetadataFetcherCoreLib().getModmeta());
		
		// Allow access to cloudflare protected urls
		System.setProperty("http.agent", "Chrome");
		
		McVersionCompare versioncompare = new McVersionCompare(mcversion);
		
		if (!versioncompare.containsVersion(ForgeVersion.mcVersion)) {
			CrashReport crash = CrashReport.makeCrashReport(new WrongMinecraftVersionException(this), "Mcversion is not supported! Allowed: " + mcversion);
			CoreLib.getLogger().error(crash.getCompleteReport());
			FMLCommonHandler.instance().exitJava(0, true);
		}
		
		UpdateChecker.add(modid, "https://www.hycrafthd.net/mods/corelib/promo.json");
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
	 * Preinit event
	 * 
	 * @param event
	 */
	@Subscribe
	public void preinit(FMLPreInitializationEvent event) {
		configuration = event.getSuggestedConfigurationFile();
	}
	
	/**
	 * Init event
	 * 
	 * @param event
	 */
	@Subscribe
	public void init(FMLInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			analytics = new Analytics(this);
		}
	}
	
	/**
	 * Postinit event
	 */
	@Subscribe
	public void postinit(FMLPostInitializationEvent event) {
		GenerationRegistry.registerWorldGenerator(new WorldGeneratorCoreLib(), 0);
		EventRegistry.register(new ProcessHandler());
		UpdateChecker.startUpdatechecking();
		if (event.getSide() == Side.CLIENT) {
			EventRegistry.register(new UpdaterInformation());
			analytics.startAnalytics();
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
	
	@Override
	public void addStatsToAnalytics(Analytics analytics) {
		String mods = "";
		for (ModContainer container : Loader.instance().getModList()) {
			mods = mods + container.getModId() + "@" + container.getVersion() + ";";
		}
		analytics.addStat("mods", mods);
	}
	
	@Override
	public boolean isAnalyticsEnabled() {
		boolean b = true;
		try {
			if (!configuration.exists()) {
				configuration.createNewFile();
			}
			FileReader fr = new FileReader(configuration);
			BufferedReader reader = new BufferedReader(fr);
			
			String k = "";
			
			Iterator<String> it = reader.lines().iterator();
			while (it.hasNext()) {
				String s = it.next();
				if (s.startsWith("enable-analytics:")) {
					k = s.split(":")[1];
				}
			}
			if (k == "") {
				FileWriter wr = new FileWriter(configuration);
				BufferedWriter writer = new BufferedWriter(wr);
				writer.write("enable-analytics:true");
				writer.flush();
				writer.close();
				k = "true";
			}
			
			b = Boolean.parseBoolean(k);
			reader.close();
		} catch (Exception ex) {
			configuration.delete();
			ex.printStackTrace();
		}
		return b;
	}
	
}
