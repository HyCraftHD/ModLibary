package net.hycrafthd.corelib.util;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;

public class MathUtil {

	public static int distance(int a, int b) {
		return Math.abs(Math.abs(a) - Math.abs(b));
	}

	public static Vec3i getMinVec(Vec3i v1, Vec3i v2) {
		int x = Math.min(v1.getX(), v2.getX());
		int y = Math.min(v1.getY(), v2.getY());
		int z = Math.min(v1.getZ(), v2.getZ());
		return new Vec3i(x, y, z);
	}

	public static Vec3i getMaxVec(Vec3i v1, Vec3i v2) {
		int x = Math.max(v1.getX(), v2.getX());
		int y = Math.max(v1.getY(), v2.getY());
		int z = Math.max(v1.getZ(), v2.getZ());
		return new Vec3i(x, y, z);
	}

	public static int getRandomNumberInRange(int min, int max) {
		return getRandomNumberInRange(new Random(), min, max);
	}

	public static float getRandomNumberInRange(float min, float max) {
		return getRandomNumberInRange(new Random(), min, max);
	}

	public static double getRandomNumberInRange(double min, double max) {
		return getRandomNumberInRange(new Random(), min, max);
	}

	public static int getRandomNumberInRange(Random rand, int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}

	public static float getRandomNumberInRange(Random rand, float min, float max) {
		return rand.nextFloat() * (max - min) + min;
	}

	public static double getRandomNumberInRange(Random rand, double min, double max) {
		return rand.nextDouble() * (max - min) + min;
	}

	public static double getAverage(long... numbers) {
		return MathHelper.average(numbers);
	}

}
