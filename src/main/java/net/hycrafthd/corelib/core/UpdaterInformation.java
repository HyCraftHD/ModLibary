package net.hycrafthd.corelib.core;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.CheckResult;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
			for (ModContainer container : Loader.instance().getActiveModList()) {
				if (!container.getModId().startsWith("mcp") && !container.getModId().equalsIgnoreCase("mcp") && !container.getModId().equalsIgnoreCase("FML") && !container.getModId().equalsIgnoreCase("Forge")) {
					CheckResult res = ForgeVersion.getResult(container);
					if ((res != null && res.status != Status.PENDING) && res.status == Status.BETA_OUTDATED || res.status == Status.OUTDATED) {
						ITextComponent comp = new TextComponentString("\u00a7eNew version (\u00a77" + res.target + "\u00a7e) for\u00a7a " + container.getName() + " \u00a7eis available for Minecraft " + ForgeVersion.mcVersion + "!\n\u00a7bDownload at: \u00a7a" + res.url);
						Style style = comp.getStyle();
						style.setColor(TextFormatting.YELLOW);
						style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("\u00a7cClick to open download page.")));
						style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, res.url));
						comp.setStyle(style);
						mc.thePlayer.addChatComponentMessage(comp);
					}
				}
			}
		}
	}

}
