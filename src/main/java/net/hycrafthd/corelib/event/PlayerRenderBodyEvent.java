package net.hycrafthd.corelib.event;

import net.hycrafthd.corelib.util.event.CoreEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerRenderBodyEvent implements CoreEvent {

	public final AbstractClientPlayer player;
	public final float partialTickTime;

	public PlayerRenderBodyEvent(AbstractClientPlayer player, float partialTickTime) {
		this.player = player;
		this.partialTickTime = partialTickTime;
	}

}
