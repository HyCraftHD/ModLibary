package net.hycrafthd.corelib.util.cschematic;

import java.io.*;

import net.hycrafthd.corelib.util.NBTUtil;
import net.minecraft.nbt.*;

public class SchematicReader {

	private File fl;
	private InputStream is;

	public SchematicReader(File fl) {
		this.fl = fl;
	}

	public SchematicReader(InputStream is) {
		this.is = is;
	}

	public File getFile() {
		return fl;
	}

	public InputStream getInputStream() {
		return is;
	}

	public ReadedSchematic read() throws IOException {

		NBTTagCompound comp = null;

		if (this.getFile() != null) {
			comp = NBTUtil.readNBTFromFile(this.fl);
		} else if (this.getInputStream() != null) {
			comp = CompressedStreamTools.readCompressed(this.is);
		}

		if (comp == null) {
			throw new NullPointerException();
		}

		NBTTagList list = (NBTTagList) comp.getTag("List");
		BlockObj[] args = new BlockObj[list.tagCount()];
		for (int i = 0; i < list.tagCount(); i++) {
			args[i] = BlockObj.fromNBT(list.getCompoundTagAt(i));
		}

		int distX = comp.getInteger("X");
		int distY = comp.getInteger("Y");
		int distZ = comp.getInteger("Z");

		return new ReadedSchematic(args, distX, distY, distZ);
	}
}
