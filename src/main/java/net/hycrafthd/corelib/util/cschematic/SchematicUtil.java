package net.hycrafthd.corelib.util.cschematic;

import java.io.File;
import java.security.spec.DSAGenParameterSpec;
import java.util.Collection;

import org.apache.commons.compress.compressors.gzip.GzipUtils;

import net.hycrafthd.corelib.util.FileUtil;
import net.hycrafthd.corelib.util.process.ProcessHandler;
import net.minecraft.command.CommandClone;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class SchematicUtil {

	public static File getSaveDirectionary() {
		File file = new File(FileUtil.getMainDirectionary(), "cschematic");
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	public static Collection<File> getSaveDirectionaryFiles() throws Exception {
		return FileUtil.FILE_UTILS.listFiles(getSaveDirectionary(), new String[] { "cschematic" }, true);
	}

	public static String[] getSaveDirectionaryFileListCSchematic() throws Exception {
		Collection<File> files = getSaveDirectionaryFiles();
		String[] n = new String[files.size()];
		int c = 0;
		for (File file : files) {
			File f = new File(file.getPath());
			String s = f.getName().replace(".cschematic", "");
			while (!f.getParentFile().getAbsolutePath().equals(getSaveDirectionary().getAbsolutePath())) {
				f = f.getParentFile();
				s = f.getName() + "/" + s;
			}
			n[c] = s;
			c++;
		}
		return n;
	}

	public static void saveRegion(World world, SchematicBoundingBox box, File file) {
		ProcessHandler.addProcess(new RegionSaver(world, box, file));
	}
}
