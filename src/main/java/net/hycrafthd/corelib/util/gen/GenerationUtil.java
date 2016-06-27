package net.hycrafthd.corelib.util.gen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

/**
 * Util class for generation
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class GenerationUtil {

	/**
	 * Generation with worldgenmineable
	 * 
	 * @param oregen
	 *            Oregen
	 * @param random
	 *            Random
	 * @param x
	 *            Chunk x
	 * @param z
	 *            Chunk z
	 * @param world
	 *            World
	 */
	public static void generateOre(OreGen oregen, Random random, int x, int z, World world) {

		int vienSize = 2 + oregen.getMinVienSize() + random.nextInt(oregen.getMaxVienSize() - oregen.getMinVienSize() + 1);
		int hightRange = oregen.getMaxY() - oregen.getMinY();

		for (int i = 0; i < oregen.getChance(); i++) {
			int posX = x + random.nextInt(16);
			int posY = random.nextInt(hightRange) + oregen.getMinY();
			int posZ = z + random.nextInt(16);
			new WorldGenMinable(oregen.getState(), vienSize, BlockHelper.forBlock(oregen.getBlockin())).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}

}
