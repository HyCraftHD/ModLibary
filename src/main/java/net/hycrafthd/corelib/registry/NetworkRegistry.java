package net.hycrafthd.corelib.registry;

import net.hycrafthd.corelib.util.ICustomGuiHandler;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class NetworkRegistry {

	public static void registerGuiHandler(ICustomGuiHandler handler) {
		net.minecraftforge.fml.common.network.NetworkRegistry.INSTANCE.registerGuiHandler(handler.getMod(), handler);
	}

	public static SimpleNetworkWrapper createSimpleNetworkChannel(String name) {
		return net.minecraftforge.fml.common.network.NetworkRegistry.INSTANCE.newSimpleChannel(name);
	}

}
