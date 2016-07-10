package net.hycrafthd.corelib.via;


import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Vertex {

	private final Vec3d vec1,vec2,vec3,vec4;
	
	@SideOnly(Side.CLIENT)
	public Vertex(Vec3d vec1,Vec3d vec2,Vec3d vec3,Vec3d vec4) {
		this.vec1 = vec1;
		this.vec2 = vec2;
		this.vec3 = vec3;
		this.vec4 = vec4;
	}

	public Vec3d getVec1() {
		return vec1;
	}
	
	public Vec3d getVec2() {
		return vec2;
	}

	public Vec3d getVec3() {
		return vec3;
	}

	public Vec3d getVec4() {
		return vec4;
	}

	
}
