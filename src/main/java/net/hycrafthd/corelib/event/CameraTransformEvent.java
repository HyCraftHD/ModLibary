package net.hycrafthd.corelib.event;

import net.hycrafthd.corelib.util.event.CoreEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Camera transform event (CLIENT SIDE ONLY)
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SideOnly(Side.CLIENT)
public class CameraTransformEvent implements CoreEvent {

	/**
	 * Clientplayer
	 */
	public final EntityPlayerSP player;
	/**
	 * Update count
	 */
	public final int rendererUpdateCount;
	/**
	 * Partial time
	 */
	public final float partialTickTime;
	/**
	 * Pass
	 */
	public final float pass;

	/**
	 * Construktor
	 * 
	 * @param player
	 * @param rendererUpdateCount
	 * @param partialTickTime
	 * @param pass
	 */
	public CameraTransformEvent(EntityPlayerSP player, int rendererUpdateCount, float partialTickTime, int pass) {
		this.player = player;
		this.rendererUpdateCount = rendererUpdateCount;
		this.partialTickTime = partialTickTime;
		this.pass = pass;
	}

}
