package net.hycrafthd.corelib.event;

import net.hycrafthd.corelib.util.event.CoreEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Cape update event (CLIENT SIDE ONLY) TODO Skin update
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SideOnly(Side.CLIENT)
public class CapeUpdatedEvent implements CoreEvent {

	/**
	 * Client player
	 */
	public final AbstractClientPlayer player;
	/**
	 * Cape resourcelocation
	 */
	public ResourceLocation res;

	/**
	 * Constructor
	 * 
	 * @param player
	 * @param res
	 */
	public CapeUpdatedEvent(AbstractClientPlayer player, ResourceLocation res) {
		this.player = player;
		this.res = res;
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
