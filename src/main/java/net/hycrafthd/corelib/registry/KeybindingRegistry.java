package net.hycrafthd.corelib.registry;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeybindingRegistry {

	public static void register(KeyBinding key) {
		ClientRegistry.registerKeyBinding(key);
	}

}
