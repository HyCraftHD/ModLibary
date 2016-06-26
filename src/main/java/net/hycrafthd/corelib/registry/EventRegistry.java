package net.hycrafthd.corelib.registry;

import net.hycrafthd.corelib.CoreLib;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Registry for EventHandler
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class EventRegistry {

	/**
	 * Register an object as event handler for MinecraftForge, FMLCommonHandler and CoreLib event bus
	 * 
	 * @param obj
	 *            Instance of event handler
	 */
	public static void register(Object obj) {
		MinecraftForge.EVENT_BUS.register(obj);
		FMLCommonHandler.instance().bus().register(obj);
		CoreLib.getInstance().getEventBus().register(obj);
	}

}
