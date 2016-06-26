package net.hycrafthd.corelib.registry;

import java.util.ArrayList;
import java.util.HashMap;

import net.hycrafthd.corelib.CoreLib;
import net.hycrafthd.corelib.util.gen.BaseWorldGenerator;
import net.hycrafthd.corelib.util.gen.OreGen;
import net.hycrafthd.corelib.util.gen.OreGenDim;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GenerationRegistry {

	public static void registerBaseWorldGenerator(BaseWorldGenerator generator, int modGenerationWeight) {
		registerWorldGenerator(generator, modGenerationWeight);
	}

	public static void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight) {
		GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
	}

	public static void registerOreForGeneration(int dimid, OreGen oregen) {
		registerOreForGeneration(new OreGenDim(dimid, oregen));
	}

	public static void registerOresForGeneration(OreGenDim... oregendims) {
		for (OreGenDim oregendim : oregendims) {
			registerOreForGeneration(oregendim);
		}
	}

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
