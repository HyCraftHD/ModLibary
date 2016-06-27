package net.hycrafthd.corelib.registry;

import net.hycrafthd.corelib.util.ICustomGuiHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Registry for network things {@link net.minecraftforge.fml.common.network.NetworkRegistry}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class NetworkRegistry {

	/**
	 * Register a guihandler
	 * 
	 * @param handler
	 *            ICustomGuiHandler
	 */
	public static void registerGuiHandler(ICustomGuiHandler handler) {
		net.minecraftforge.fml.common.network.NetworkRegistry.INSTANCE.registerGuiHandler(handler.getMod(), handler);
	}

	/**
	 * Create a new simple network channel
	 * 
	 * @param name
	 *            Channel name
	 * @return Instance of simple network wrapper for packet managing, etc.
	 */
	public static SimpleNetworkWrapper createSimpleNetworkChannel(String name) {
		return net.minecraftforge.fml.common.network.NetworkRegistry.INSTANCE.newSimpleChannel(name);
	}

}
