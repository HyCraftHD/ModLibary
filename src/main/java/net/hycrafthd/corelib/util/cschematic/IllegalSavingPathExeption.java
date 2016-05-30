package net.hycrafthd.corelib.util.cschematic;

import net.minecraft.command.CommandException;

public class IllegalSavingPathExeption extends CommandException {

	public IllegalSavingPathExeption() {
		super("The saving Path is not allowed");
	}

}
