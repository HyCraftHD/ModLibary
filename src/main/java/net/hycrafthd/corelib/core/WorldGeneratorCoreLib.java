package net.hycrafthd.corelib.core;

import java.util.*;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.gen.*;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * Worldgenerator for CoreLib
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class WorldGeneratorCoreLib implements IWorldGenerator {

	/**
	 * Generate
	 */
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		HashMap<Integer, ArrayList<OreGen>> gen = CoreLib.getInstance().getGenerationList();
		int x = chunkX * 16;
		int z = chunkZ * 16;
		if (gen.get(world.provider.getDimensionId()) != null) {
			List<OreGen> list = gen.get(world.provider.getDimensionId());
			for (OreGen oregen : list) {
				GenerationUtil.generateOre(oregen, random, x, z, world);
			}
		}
	}

}
