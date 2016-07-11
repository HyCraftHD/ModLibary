package net.hycrafthd.corelib.util.cschematic;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class SchematicBuilder {

	private final SchematicReader reader;
	private final World worldObj;

	public SchematicBuilder(SchematicReader reader, World world) {
		this.reader = reader;
		this.worldObj = world;
	}

	public SchematicReader getReader() {
		return reader;
	}

	public void build(final BlockPos pos, final boolean b) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Thread.currentThread().setName("Schematic Builder");
				try {
					ReadedSchematic re = reader.read();
					BlockObj[] objs = re.getObjts();
					int i = 0;
					for (int z = 0; z < re.getDistZ(); z++) {
						for (int y = 0; y < re.getDistY(); y++) {
							for (int x = 0; x < re.getDistX(); x++) {
								BlockObj obj = objs[i];
								BlockPos p = null;
								if (b) {
									p = pos.add(new Vec3i(x, y, z));
								} else {
									p = pos.add(new Vec3i(z, y, x));
								}
								worldObj.setBlockState(p, obj.getBlock().getStateFromMeta(obj.getMeta()));
								/*
								 * CoreLib.getLogger().info(obj.getBlock().toString()); CoreLib.getLogger().info("" + obj.getMeta());
								 * CoreLib.getLogger().info(obj.getBlock().getStateFromMeta(obj.getMeta()).toString()); CoreLib.getLogger().info(p.toString()); CoreLib.getLogger().info("Count:" + i);
								 */
								TileEntity ent = worldObj.getTileEntity(p);
								if (ent != null && obj.hasNBT()) {
									// CoreLib.getLogger().info("true");
									// CoreLib.getLogger().info(obj.getTileEntity().toString());
									ent.readFromNBT(obj.getTileEntity());
									ent.setPos(p);
								}
								i++;
							}
						}
					}
				} catch (Throwable thr) {
					thr.printStackTrace();
				}
			}
		}).start();
	}

	public World getWorld() {
		return worldObj;
	}
}
