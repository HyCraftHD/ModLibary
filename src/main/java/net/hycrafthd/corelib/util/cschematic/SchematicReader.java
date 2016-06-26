package net.hycrafthd.corelib.util.cschematic;

import java.io.File;
import java.io.IOException;

import net.hycrafthd.corelib.util.NBTUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SchematicReader {

	private final File fl;
	
	public SchematicReader(File fl) {
		this.fl = fl;
	}

	public File getFile() {
		return fl;
	}
	
	public ReadedSchematic read() throws IOException{
		NBTTagCompound comp = NBTUtil.readNBTFromFile(this.fl);
		
        NBTTagList list = (NBTTagList)comp.getTag("List");
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
