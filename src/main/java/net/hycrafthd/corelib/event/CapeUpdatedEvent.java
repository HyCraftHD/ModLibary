package net.hycrafthd.corelib.event;

import net.hycrafthd.corelib.util.event.CoreEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CapeUpdatedEvent implements CoreEvent {

	public final AbstractClientPlayer player;
	public ResourceLocation res;

	public CapeUpdatedEvent(AbstractClientPlayer player, ResourceLocation res) {
		this.player = player;
		this.res = res;
	}

	public void setRes(ResourceLocation res) {
		this.res = res;
	}

	public ResourceLocation getRes() {
		return res;
	}

}
