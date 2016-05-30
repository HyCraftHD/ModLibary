package net.hycrafthd.corelib.util.cschematic;

import javafx.geometry.Point3D;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.util.Vec3i;

public class Point3i extends Vec3i {

	private final static Point3i NULL = new Point3i(0, 0, 0);

	public Point3i(double x, double y, double z) {
		super(x, y, z);
	}

	public Point3i(int x, int y, int z) {
		super(x, y, z);
	}

	public Point3i(Point3i point) {
		this(point.getX(), point.getY(), point.getZ());
	}

	public Point3i() {
		this(NULL);
	}

	public Point3i add(int x, int y, int z) {
		return new Point3i(getX() + x, getY() + y, getZ() + z);
	}

	public Point3i substract(int x, int y, int z) {
		return new Point3i(getX() - x, getY() - y, getZ() - z);
	}

	public Point3i multiply(int x, int y, int z) {
		return new Point3i(getX() * x, getY() * y, getZ() * z);
	}

	public Point3i divide(int x, int y, int z) {
		return new Point3i(getX() / x, getY() / y, getZ() / z);
	}

	public Point3i rotate(EnumAxis axis, float angle) {
		if (axis == EnumAxis.X) {
			return new Point3i(getX(), getY() * Math.cos(angle) - getZ() * Math.sin(angle), getY() * Math.sin(angle) - getZ() * Math.cos(angle));
		} else if (axis == EnumAxis.Y) {
			return new Point3i(getZ() * Math.sin(angle) - getX() * Math.cos(angle), getY(), getZ() * Math.cos(angle) - getX() * Math.sin(angle));
		} else if (axis == EnumAxis.Z) {
			return new Point3i(getX() * Math.cos(angle) - getY() * Math.sin(angle), getX() * Math.sin(angle) - getY() * Math.cos(angle), getZ());
		} else {
			return NULL;
		}
	}

}
