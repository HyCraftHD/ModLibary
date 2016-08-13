package net.hycrafthd.corelib.core;

import java.util.Map.Entry;

import net.hycrafthd.corelib.util.UpdateChecker;
import net.hycrafthd.corelib.util.UpdateChecker.CheckResult;
import net.minecraft.client.Minecraft;
import net.minecraft.event.*;
import net.minecraft.util.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.*;

/**
 * Displays updates in chat
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SideOnly(Side.CLIENT)
public class UpdaterInformation {
	
	private boolean show = false;
	
	@SubscribeEvent
	public void firstRender(RenderGameOverlayEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		if (!mc.inGameHasFocus || mc.thePlayer == null) {
			return;
		}
		if (!show) {
			show = true;
			for (Entry<String, CheckResult> modid : UpdateChecker.updateMods.entrySet()) {
				CheckResult res = modid.getValue();
				if ((res != null && res.status != Status.PENDING) && res.status == Status.BETA_OUTDATED || res.status == Status.OUTDATED) {
					IChatComponent comp = new ChatComponentText("\u00a7eNew version (\u00a77" + res.target + "\u00a7e) for\u00a7a " + res.name + " \u00a7eis available for Minecraft " + ForgeVersion.mcVersion + "!\n\u00a7bDownload at: \u00a7a" + res.url);
					ChatStyle style = comp.getChatStyle();
					style.setColor(EnumChatFormatting.YELLOW);
					style.setChatHoverEvent(new HoverEvent(net.minecraft.event.HoverEvent.Action.SHOW_TEXT, new ChatComponentText("\u00a7cClick to open download page.")));
					style.setChatClickEvent(new ClickEvent(net.minecraft.event.ClickEvent.Action.OPEN_URL, res.url));
					comp.setChatStyle(style);
					mc.thePlayer.addChatComponentMessage(comp);
				}
			}
		}
	}
}
