package net.hycrafthd.corelib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtil {

	public static void writeNBTToFile(NBTTagCompound tag, File file) throws IOException {
		FileOutputStream stream = new FileOutputStream(file);
		try {
			CompressedStreamTools.writeCompressed(tag, stream);
		} finally {
			stream.flush();
			stream.close();
		}
	}
	
	public static NBTTagCompound readNBTFromFile(File file) throws IOException {
		FileInputStream stream = new FileInputStream(file);
		NBTTagCompound tag;
		try {
			tag = CompressedStreamTools.readCompressed(stream);
		} finally {
			stream.close();
		}
		return tag;
	}

}
