package net.hycrafthd.corelib.util.cschematic;

import java.io.File;
import net.hycrafthd.corelib.util.NBTUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SchematicWriter {

	private final Schematic schematic;
	
	public SchematicWriter(Schematic args) {
		this.schematic = args;
	}

	public Schematic getSchematic() {
		return schematic;
	}
	
	public void write(final File name){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
				BlockObj[] blockar = schematic.getBlocks();
				NBTTagList list = new NBTTagList();
				for (BlockObj blockObj : blockar) {
					if(blockObj != null){
					NBTTagCompound comp = blockObj.toNBT();
					list.appendTag(comp);
					}
				}
				NBTTagCompound tag = new NBTTagCompound();
				tag.setTag("List", list);
				tag.setInteger("X", schematic.disx);
				tag.setInteger("Y", schematic.disy);
				tag.setInteger("Z", schematic.disz);
				NBTUtil.writeNBTToFile(tag, name);
				}catch(Throwable th){
					System.err.println("Failed to Save Schematic");
			    }
			}
		}).start();
	}
	
}
