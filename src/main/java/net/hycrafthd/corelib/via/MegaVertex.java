package net.hycrafthd.corelib.via;

import net.minecraft.util.Vec3;

public class MegaVertex implements BaseVertex{

	private Vec3[] points;
	
	/**
	 * Same as {@link Vertex} with more or less Points
	 * 
	 * @author MrTroble
	 * 
	 * @since 1.0.1
	 * 
	 * @see Vertex
	 */
	
	public MegaVertex(Vec3... vecs) {
		this.points = vecs;
	}
	
	public Vec3[] getVertex(){
		return points;
	}
	
	
}
