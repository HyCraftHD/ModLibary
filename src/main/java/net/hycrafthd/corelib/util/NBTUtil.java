package net.hycrafthd.corelib.util;

import java.io.*;

import net.minecraft.nbt.*;

/**
 * Utils for Colors ({@link NBTTagCompound})
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class NBTUtil {

	/**
	 * Write NBTTag to file
	 * 
	 * @param tag
	 *            NBTTagCompound
	 * @param file
	 *            File
	 * @throws IOException
	 */
	public static void writeNBTToFile(NBTTagCompound tag, File file) throws IOException {
		FileOutputStream stream = new FileOutputStream(file);
		try {
			CompressedStreamTools.writeCompressed(tag, stream);
		} finally {
			stream.flush();
			stream.close();
		}
	}

	/**
	 * Read NBTTag from file
	 * 
	 * @param file
	 *            File
	 * @return NBTTagCompound
	 * @throws IOException
	 */
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
