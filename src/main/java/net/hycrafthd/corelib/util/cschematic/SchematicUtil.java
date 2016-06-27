package net.hycrafthd.corelib.util.cschematic;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import net.hycrafthd.corelib.util.FileUtil;

public class SchematicUtil {

	public static File getSaveDirectionary() {
		File file = new File(FileUtil.getMainDirectory(), "cschematic");
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	public static Collection<File> getSaveDirectionaryFiles() throws Exception {
		return FileUtils.listFiles(getSaveDirectionary(), new String[] { "cschematic" }, true);
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
}