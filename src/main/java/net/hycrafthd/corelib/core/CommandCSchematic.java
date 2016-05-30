package net.hycrafthd.corelib.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import akka.io.Tcp.Command;
import net.hycrafthd.corelib.util.FileUtil;
import net.hycrafthd.corelib.util.NBTUtil;
import net.hycrafthd.corelib.util.cschematic.AbsoluteRegion;
import net.hycrafthd.corelib.util.cschematic.IllegalSavingPathExeption;
import net.hycrafthd.corelib.util.cschematic.RelativeRegion;
import net.hycrafthd.corelib.util.cschematic.SchematicBoundingBox;
import net.hycrafthd.corelib.util.cschematic.SchematicUtil;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandClone;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.command.server.CommandScoreboard;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.LanguageRegistry;

public class CommandCSchematic extends CommandBase {

	private String lang = "command.cschematic";

	@Override
	public String getName() {
		return "cschematic";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return lang + ".usage";
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		World world = player.getEntityWorld();
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("save")) {
				if (args.length != 8) {
					throw new WrongUsageException(getCommandUsage(sender) + ".save");
				}
				try {
					String name = args[1];
					BlockPos pos1 = func_175757_a(sender, args, 2, false);
					BlockPos pos2 = func_175757_a(sender, args, 5, false);

					File file = new File(SchematicUtil.getSaveDirectionary(), name + ".cschematic");

					if (file.getAbsolutePath().contains("..")) {
						throw new IllegalSavingPathExeption();
					}

					File parent = file.getParentFile();

					if (!parent.getAbsolutePath().equals(SchematicUtil.getSaveDirectionary().getAbsolutePath()) && !parent.exists()) {
						parent.mkdir();
					}

					SchematicUtil.saveRegion(world, new SchematicBoundingBox(pos1, pos2), file);

					// AbsoluteRegion abreg = new AbsoluteRegion(world, pos1, pos2);
					// RelativeRegion relrg = abreg.toRelative();
					//
					// File file = new File(SchematicUtil.getSaveDirectionary(), name + ".cschematic");
					//
					// if (file.getAbsolutePath().contains("..")) {
					// throw new IllegalSavingPathExeption();
					// }
					//
					// File parent = file.getParentFile();
					//
					// if (!parent.getAbsolutePath().equals(SchematicUtil.getSaveDirectionary().getAbsolutePath()) && !parent.exists()) {
					// parent.mkdir();
					// }
					//
					// NBTUtil.writeNBTToFile(relrg.save(), file);

					notifyOperators(player, this, lang + ".success.save", pos1, pos2, name);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new CommandException(lang + ".error", "saving", ex.getClass().getName());
				}
			} else if (args[0].equalsIgnoreCase("load")) {
				if (args.length != 2) {
					throw new WrongUsageException(getCommandUsage(sender) + ".load");
				}
				try {
					String name = args[1];
					BlockPos pos = player.getPosition();

					File file = new File(SchematicUtil.getSaveDirectionary(), name + ".cschematic");

					if (file.getAbsolutePath().contains("..")) {
						throw new IllegalSavingPathExeption();
					}

					NBTTagCompound tag = NBTUtil.readNBTFromFile(file);
					RelativeRegion relrg = RelativeRegion.load(tag);
					AbsoluteRegion abreg = AbsoluteRegion.fromRelative(relrg, world, pos);

					notifyOperators(player, this, lang + ".success.load", name, abreg.getPos1(), abreg.getPos2());
				} catch (Exception ex) {
					throw new CommandException(lang + ".error", "loading", ex.getClass().getName());
				}
			} else {
				throw new WrongUsageException(getCommandUsage(sender));
			}
		} else {
			throw new WrongUsageException(getCommandUsage(sender));
		}
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, new String[] { "save", "load" });
		} else {
			if (args[0].equalsIgnoreCase("save")) {
				if (args.length == 2) {
					return getListOfStringsMatchingLastWord(args, "name");
				}
				if (args.length >= 3 && args.length <= 5) {
					return func_175771_a(args, 2, pos);
				}
				if (args.length >= 5 && args.length <= 8) {
					return func_175771_a(args, 5, pos);
				}
			} else if (args[0].equalsIgnoreCase("load")) {
				if (args.length == 2) {
					try {
						return getListOfStringsMatchingLastWord(args, SchematicUtil.getSaveDirectionaryFileListCSchematic());
					} catch (Exception ex) {
					}
				}
			}
		}
		return null;
	}

}
