package net.hycrafthd.corelib.via;

import net.minecraft.util.math.Vec3d;

public class MegaVertex implements BaseVertex{

	private Vec3d[] points;
	
	/**
	 * Same as {@link Vertex} with more or less Points
	 * 
	 * @author MrTroble
	 * 
	 * @since 1.0.1
	 * 
	 * @see Vertex
	 */
	
	public MegaVertex(Vec3d... vecs) {
		this.points = vecs;
	}
	
	public Vec3d[] getVertex(){
		return points;
	}
	
	
}
