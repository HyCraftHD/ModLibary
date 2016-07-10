package net.hycrafthd.corelib.core;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

import net.hycrafthd.corelib.util.cschematic.Schematic;
import net.hycrafthd.corelib.util.cschematic.SchematicBuilder;
import net.hycrafthd.corelib.util.cschematic.SchematicReader;
import net.hycrafthd.corelib.util.cschematic.SchematicUtil;
import net.hycrafthd.corelib.util.cschematic.SchematicWriter;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Minecraft command to save and load cschematics
 * 
 * @author HyCraftHD (https://www.hycrafthd.net), MrTroble
 *
 */
public class CommandCschematic extends CommandBase {

	/**
	 * Language data key
	 */
	private String lang = "command.cschematic";

	/**
	 * Command name
	 */
	@Override
	public String getCommandName() {
		return "cschematic";
	}

	/**
	 * Command permission
	 */
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	/**
	 * Command usage
	 */
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return lang + ".usage";
	}

	/**
	 * Command executed
	 */
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		World world = player.getEntityWorld();
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("save")) {
				if (args.length != 8) {
					throw new WrongUsageException(getCommandUsage(sender) + ".save");
				}
				try {
					String name = args[1];
					//FIXME waht is the updated deobfuscated name of that function? (func_175757_a)
					BlockPos pos1 = func_175757_a(sender, args, 2, false);
					BlockPos pos2 = func_175757_a(sender, args, 5, false);

					File file = new File(SchematicUtil.getSaveDirectionary(), name + ".cschematic");

					if (!file.getParent().contains("cschematic")) {
						throw new IllegalArgumentException();
					}

					File parent = file.getParentFile();

					if (!parent.getAbsolutePath().equals(SchematicUtil.getSaveDirectionary().getAbsolutePath()) && !parent.exists()) {
						parent.mkdir();
					}

					Schematic sch = new Schematic(pos1, pos2, world);
					SchematicWriter writer = new SchematicWriter(sch);
					writer.write(file);

					//FIXME --> new name of that method??
					notifyOperators(player, this, lang + ".success.save", pos1, pos2, name);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new CommandException(lang + ".error", "saving", ex.getClass().getName());
				}
			} else if (args[0].equalsIgnoreCase("load")) {
				System.out.println(args.length);
				if (args.length != 2 && args.length != 3) {
					throw new WrongUsageException(getCommandUsage(sender) + ".load");
				}
				try {
					String name = args[1];
					BlockPos pos = player.getPosition();

					File file = new File(SchematicUtil.getSaveDirectionary(), name + ".cschematic");

					if (!file.getParent().contains("cschematic")) {
						throw new IllegalArgumentException();
					}

					SchematicReader reader = new SchematicReader(file);
					SchematicBuilder builder = new SchematicBuilder(reader, world);
					if (args.length == 3) {
						builder.build(pos, !Boolean.parseBoolean(args[2]));
					} else {
						builder.build(pos, true);
					}

					//FIXME (same as above)
					notifyOperators(player, this, lang + ".success.load", name);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new CommandException(lang + ".error", "loading", ex.getClass().getName());
				}
			} else {
				throw new WrongUsageException(getCommandUsage(sender));
			}
		} else {
			throw new WrongUsageException(getCommandUsage(sender));
		}
	}

	/**
	 * Command Tab completion
	 */
	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, new String[] { "save", "load" });
		} else {
			if (args[0].equalsIgnoreCase("save")) {
				if (args.length == 2) {
					return getListOfStringsMatchingLastWord(args, "name");
				}
				//FIXME updated deobfuscated name of func_175771_a
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
				} else if (args.length == 3) {
					return Lists.newArrayList("true", "false");
				}
			}
		}
		return null;
	}

}
