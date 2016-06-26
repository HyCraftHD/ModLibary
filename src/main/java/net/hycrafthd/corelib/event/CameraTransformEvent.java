package net.hycrafthd.corelib.event;

import net.hycrafthd.corelib.util.event.CoreEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CameraTransformEvent implements CoreEvent {

	public final EntityPlayerSP player;
	public final int rendererUpdateCount;
	public final float partialTickTime;
	public final float pass;

	public CameraTransformEvent(EntityPlayerSP player, int rendererUpdateCount, float partialTickTime, int pass) {
		this.player = player;
		this.rendererUpdateCount = rendererUpdateCount;
		this.partialTickTime = partialTickTime;
		this.pass = pass;
	}

}
