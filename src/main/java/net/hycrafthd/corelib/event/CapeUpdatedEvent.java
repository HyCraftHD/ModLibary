package net.hycrafthd.corelib.event;

import net.hycrafthd.corelib.util.event.CoreEvent;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

/**
 * Cape update event (CLIENT SIDE ONLY)
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SideOnly(Side.CLIENT)
public class CapeUpdatedEvent implements CoreEvent {
	
	/**
	 * Network player info
	 */
	public final NetworkPlayerInfo info;
	/**
	 * Cape resourcelocation
	 */
	private ResourceLocation res;
	
	/**
	 * Constructor
	 * 
	 * @param player
	 * @param res
	 */
	
	public CapeUpdatedEvent(NetworkPlayerInfo network) {
		this.info = network;
		this.res = network.getLocationCape();
	}
	
	/**
	 * Setter for res
	 * 
	 * @param res
	 *            ResourceLocation
	 */
	public void setRes(ResourceLocation res) {
		this.res = res;
	}
	
	/**
	 * Getter for res
	 * 
	 * @return ResourceLocation
	 */
	public ResourceLocation getRes() {
		return res;
	}
	
}
