package net.hycrafthd.corelib.util;

import java.util.Random;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;



/**
 * Util methods for Math operations
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class MathUtil {

	/**
	 * Distance between 2 numbers. Only positive values
	 * 
	 * @param a
	 *            Number 1
	 * @param b
	 *            Number 2
	 * @return
	 */
	public static int distance(int a, int b) {
		return Math.abs(Math.abs(a) - Math.abs(b));
	}

	/**
	 * Get Minum Vector from 2 Vectors
	 * 
	 * @param v1
	 *            Vector 1
	 * @param v2
	 *            Vector 2
	 * @return Minimum Vector
	 */
	public static Vec3i getMinVec(Vec3i v1, Vec3i v2) {
		int x = Math.min(v1.getX(), v2.getX());
		int y = Math.min(v1.getY(), v2.getY());
		int z = Math.min(v1.getZ(), v2.getZ());
		return new Vec3i(x, y, z);
	}

	/**
	 * Get Maximum Vector from 2 Vectors
	 * 
	 * @param v1
	 *            Vector 1
	 * @param v2
	 *            Vector 2
	 * @return Maximum Vector
	 */
	public static Vec3i getMaxVec(Vec3i v1, Vec3i v2) {
		int x = Math.max(v1.getX(), v2.getX());
		int y = Math.max(v1.getY(), v2.getY());
		int z = Math.max(v1.getZ(), v2.getZ());
		return new Vec3i(x, y, z);
	}

	/**
	 * Random number in range includes min and max. (e.g min = 2; max = 4; return: {2, 3, 4})
	 * 
	 * @param min
	 * @param max
	 * @return Random range
	 */
	public static int getRandomNumberInRange(int min, int max) {
		return getRandomNumberInRange(new Random(), min, max);
	}

	/**
	 * Random number in range includes min and max
	 * 
	 * @param min
	 * @param max
	 * @return Random range
	 */
	public static float getRandomNumberInRange(float min, float max) {
		return getRandomNumberInRange(new Random(), min, max);
	}

	/**
	 * Random number in range includes min and max
	 * 
	 * @param min
	 * @param max
	 * @return Random range
	 */
	public static double getRandomNumberInRange(double min, double max) {
		return getRandomNumberInRange(new Random(), min, max);
	}

	/**
	 * Random number in range includes min and max. (e.g min = 2; max = 4; return: {2, 3, 4})
	 * 
	 * @param rand
	 * @param min
	 * @param max
	 * @return Random range
	 */
	public static int getRandomNumberInRange(Random rand, int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}

	/**
	 * Random number in range includes min and max
	 * 
	 * @param rand
	 * @param min
	 * @param max
	 * @return Random range
	 */
	public static float getRandomNumberInRange(Random rand, float min, float max) {
		return rand.nextFloat() * (max - min) + min;
	}

	/**
	 * Random number in range includes min and max
	 * 
	 * @param rand
	 * @param min
	 * @param max
	 * @return Random range
	 */
	public static double getRandomNumberInRange(Random rand, double min, double max) {
		return rand.nextDouble() * (max - min) + min;
	}

	/**
	 * Average from longs
	 * 
	 * @param numbers
	 * @return Average
	 */
	public static double getAverage(long... numbers) {
		return MathHelper.average(numbers);
	}

}
