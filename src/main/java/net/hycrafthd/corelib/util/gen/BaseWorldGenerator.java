package net.hycrafthd.corelib.util.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * Custom worldgenerator for default dimensions
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public abstract class BaseWorldGenerator implements IWorldGenerator {

	/**
	 * Nether
	 * 
	 * @param random
	 *            Random
	 * @param x
	 *            Chunk X
	 * @param z
	 *            Chunk Z
	 * @param world
	 *            World
	 */
	protected abstract void nether(Random random, int x, int z, World world);

	/**
	 * Overworld
	 * 
	 * @param random
	 *            Random
	 * @param x
	 *            Chunk X
	 * @param z
	 *            Chunk Z
	 * @param world
	 *            World
	 */
	protected abstract void overworld(Random random, int x, int z, World world);

	/**
	 * End
	 * 
	 * @param random
	 *            Random
	 * @param x
	 *            Chunk X
	 * @param z
	 *            Chunk Z
	 * @param world
	 *            World
	 */
	protected abstract void end(Random random, int x, int z, World world);

	/**
	 * Generate method from {@link IWorldGenerator}
	 */
	@Override
	public final void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		switch (world.provider.getDimension()) {
		case -1:
			nether(random, x, z, world);
			break;
		case 0:
			overworld(random, x, z, world);
			break;
		case 1:
			end(random, x, z, world);
			break;
		}
	}
}
