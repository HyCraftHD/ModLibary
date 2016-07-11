package net.hycrafthd.corelib.event;

import net.hycrafthd.corelib.util.event.CoreEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.fml.relauncher.*;

/**
 * Player render body event (CLIENT SIDE ONLY)
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SideOnly(Side.CLIENT)
public class PlayerRenderBodyEvent implements CoreEvent {

	/**
	 * Client player
	 */
	public final AbstractClientPlayer player;
	/**
	 * Partial time
	 */
	public final float partialTickTime;

	/**
	 * Construktor
	 * 
	 * @param player
	 * @param partialTickTime
	 */
	public PlayerRenderBodyEvent(AbstractClientPlayer player, float partialTickTime) {
		this.player = player;
		this.partialTickTime = partialTickTime;
	}

}
