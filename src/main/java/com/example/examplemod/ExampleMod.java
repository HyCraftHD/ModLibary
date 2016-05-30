package com.example.examplemod;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import com.mojang.realmsclient.client.FileUpload;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.event.CameraTransformEvent;
import net.hycrafthd.corelib.event.PlayerRenderBodyEvent;
import net.hycrafthd.corelib.registry.GenerationRegistry;
import net.hycrafthd.corelib.util.FileUtil;
import net.hycrafthd.corelib.util.NBTUtil;
import net.hycrafthd.corelib.util.event.SubscribeCoreEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {

	public static final String MODID = "examplemod";
	public static final String VERSION = "1.0";

	@EventHandler
	public void init(FMLInitializationEvent event) {
		CoreLib.getInstance().getEventBus().register(this);
	}

	@SubscribeCoreEvent
	public void test(PlayerRenderBodyEvent event) {
	}

	@SubscribeCoreEvent
	public void test5g(CameraTransformEvent event) {
	}

}
