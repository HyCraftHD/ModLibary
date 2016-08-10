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
	
	/**
	 * Read NBTTag from inputstream
	 * 
	 * @param stream
	 *            Stream
	 * @return NBTTagCompound
	 * @throws IOException
	 */
	public static NBTTagCompound readNBTFromStream(InputStream stream) throws IOException {
		NBTTagCompound tag;
		try {
			tag = CompressedStreamTools.readCompressed(stream);
		} finally {
			stream.close();
		}
		return tag;
	}
	
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
	 * Write NBTTag to outputstream
	 * 
	 * @param tag
	 *            NBTTagCompound
	 * @param stream
	 *            Stream
	 * @throws IOException
	 */
	public static void writeNBTToFile(NBTTagCompound tag, OutputStream stream) throws IOException {
		try {
			CompressedStreamTools.writeCompressed(tag, stream);
		} finally {
			stream.flush();
			stream.close();
		}
	}
	
}
