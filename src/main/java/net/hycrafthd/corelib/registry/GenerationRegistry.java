package net.hycrafthd.corelib.registry;

import java.util.ArrayList;
import java.util.HashMap;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.gen.BaseWorldGenerator;
import net.hycrafthd.corelib.util.gen.OreGen;
import net.hycrafthd.corelib.util.gen.OreGenDim;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Registry for world generation ({@link IWorldGenerator})
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class GenerationRegistry {

	/**
	 * Register a {@link BaseWorldGenerator}
	 * 
	 * @param generator
	 *            Baseworldgenerator instance
	 * @param modGenerationWeight
	 *            Worldgeneration order
	 */
	public static void registerBaseWorldGenerator(BaseWorldGenerator generator, int modGenerationWeight) {
		registerWorldGenerator(generator, modGenerationWeight);
	}

	/**
	 * Register an {@link IWorldGenerator}
	 * 
	 * @param generator
	 *            Instance of implemention of IWorldgenerator
	 * @param modGenerationWeight
	 *            Worldgeneration order
	 */
	public static void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight) {
		GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
	}

	/**
	 * Register a block to worldgeneration
	 * 
	 * @param dimid
	 *            Dimension id (-1 nether, 0 surface, 1 end)
	 * @param oregen
	 *            Oregen instance
	 */
	public static void registerOreForGeneration(int dimid, OreGen oregen) {
		registerOreForGeneration(new OreGenDim(dimid, oregen));
	}

	/**
	 * Register blocks to worldgeneration
	 * 
	 * @param oregendims
	 *            Oregendim instances
	 */
	public static void registerOresForGeneration(OreGenDim... oregendims) {
		for (OreGenDim oregendim : oregendims) {
			registerOreForGeneration(oregendim);
		}
	}

	/**
	 * Register a block to worldgeneration
	 * 
	 * @param oregendim
	 *            Oregendim instance
	 */
	public static void registerOreForGeneration(OreGenDim oregendim) {
		HashMap<Integer, ArrayList<OreGen>> gen = CoreLib.getInstance().getGenerationList();
		if (gen.get(oregendim.getDimid()) == null) {
			ArrayList<OreGen> list = new ArrayList<OreGen>();
			list.add(oregendim.getOreGen());
			gen.put(oregendim.getDimid(), list);
		} else {
			gen.get(oregendim.getDimid()).add(oregendim);
		}
	}

}
