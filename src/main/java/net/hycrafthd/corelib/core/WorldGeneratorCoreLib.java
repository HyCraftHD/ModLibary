package net.hycrafthd.corelib.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.gen.GenerationUtil;
import net.hycrafthd.corelib.util.gen.OreGen;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorCoreLib implements IWorldGenerator {

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
