package net.hycrafthd.corelib.via;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VIAFile{

	public String File;
	private boolean Proceed;
	
	/**
	 * Register A VIA File
	 * 
	 * Vertex Interpretion API (VIA)
	 * is an API to easely create a vertex draw
	 * by using an external file
	 * 
	 * @param String arg - The file name
	 * 
	 * @author MrTroble
	 */
	@SideOnly(Side.CLIENT)
	public VIAFile(ResourceLocation arg0) {
		try {
			DataInputStream stream = new DataInputStream(Minecraft.getMinecraft().mcDefaultResourcePack.getInputStreamAssets(arg0));
			ArrayList<Byte> ar = new ArrayList<Byte>();
			while(stream.available() > 0){
				ar.add((byte) stream.read());
			}
			byte[] bty = new byte[ar.size()];
			int i = 0;
			for(Byte b : ar){
				bty[i] = b;
				i++;
			}
			File = new String(bty);
			stream.close();
			Proceed = true;
		} catch (FileNotFoundException e) {
			Proceed = false;
		} catch (IOException e) {
			Proceed = false;
		}
	}

	/**THIS IS THE NECESSARY METHOD
	 * 
	 * @param int Group - count of Group 
	 * after order in file 
	 * 
	 * @return The Vertex of the group to draw
	 */
	public Vertex interpretVertex(int gr){
		if(!Proceed)return null;		
		return new Vertex(getPoint(gr, 0), getPoint(gr, 1), getPoint(gr, 2), getPoint(gr, 3));
	}
	
	public String[] splitGroups(){
		if(!Proceed)return null;
		String[] arrgs = File.split("VGS:");
		String ret;
		if(arrgs.length < 2){
			ret = arrgs[0];
		}else{
			ret = arrgs[1];
		}
		String[] args = ret.split(":VGE")[0].split("VG:");
		ArrayList<String> strs = new ArrayList<String>();
		for (String string : args) {
			string = string.replace(" ", "").replace(String.format("%n"), "");
			if(string != "" || string != null){
				strs.add(string);
			}
		}
		String[] retu = new String[strs.size()];
		int i = 0;
		for (String string : strs) {
			retu[i] = string;
			i++;
		}
        return args;
	}
	
	public String[] splitPoints(int vg){
		if(!Proceed)return null;
		String group = splitGroups()[vg + 1];
		String[] arr = group.trim().split("verPoint");
		ArrayList<String> strs = new ArrayList<String>();
		for (String string : arr) {
			string = string.trim();
			String ref = string.replace(")", "~").split("~")[0].replace("(", "");
			if(ref != null && !ref.isEmpty())
			strs.add(ref);
		}
		String[] retu = new String[strs.size()];
		int i = 0;
		for (String string : strs) {
			retu[i] = string;
			i++;
		}
		return retu;
	}
	
	public Vec3d getPoint(int vg,int i){
		String[] args = splitPoints(vg)[i].split(",");
		return new Vec3d(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
	}
	
	public int getMaxGroups(){
		return splitGroups().length - 1;
	}
	
	public int getMaxPointsOfGroup(int gr){
		return splitPoints(gr).length - 1;
	}
	
	public boolean canProceed(){
		return Proceed;
	}
}
