package net.hycrafthd.corelib.util.cschematic;

import net.minecraft.util.Vec3i;

public class AreaNotLoadedExeption extends RuntimeException {

	public AreaNotLoadedExeption(Vec3i from, Vec3i to) {
		super("World not loaded in " + from.toString() + " to " + to.toString());
	}
	
}
