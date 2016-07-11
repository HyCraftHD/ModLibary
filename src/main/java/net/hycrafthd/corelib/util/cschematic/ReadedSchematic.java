package net.hycrafthd.corelib.util.cschematic;

/**
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class ReadedSchematic {
	
	private BlockObj[] objts;
	private int distX, distY, distZ;
	
	public ReadedSchematic(BlockObj[] objts, int distX, int distY, int distZ) {
		this.objts = objts;
		this.distX = distX;
		this.distY = distY;
		this.distZ = distZ;
	}
	
	public BlockObj[] getObjts() {
		return objts;
	}
	
	public void setObjts(BlockObj[] objts) {
		this.objts = objts;
	}
	
	public int getDistX() {
		return distX;
	}
	
	public void setDistX(int distX) {
		this.distX = distX;
	}
	
	public int getDistY() {
		return distY;
	}
	
	public void setDistY(int distY) {
		this.distY = distY;
	}
	
	public int getDistZ() {
		return distZ;
	}
	
	public void setDistZ(int distZ) {
		this.distZ = distZ;
	}
	
}
