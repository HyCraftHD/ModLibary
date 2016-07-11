package net.hycrafthd.corelib.registry;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.*;

/**
 * Registry for {@link KeyBinding} (CLIENT SIDE ONLY)
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@SideOnly(Side.CLIENT)
public class KeybindingRegistry {

	/**
	 * Register a new Keybind
	 * 
	 * @param key
	 *            Keybinding instance
	 */
	public static void register(KeyBinding key) {
		ClientRegistry.registerKeyBinding(key);
	}

}
