package net.hycrafthd.corelib.util.cschematic;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class SchematicBuilder {

	private final SchematicReader reader;
	private final World worldObj;
	
	public SchematicBuilder(SchematicReader reader,World world) {
		this.reader = reader;
		this.worldObj = world;
	}

	public SchematicReader getReader() {
		return reader;
	}
	
	public void build(final BlockPos pos){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
				ReadedSchematic re = reader.read();
				BlockObj[] obs = re.getObjts();
				int i = 0;
				for (int z = 0; z < re.getDistZ(); z++) {
					for (int y = 0; y < re.getDistY(); y++) {
						for (int x = 0; x < re.getDistZ(); x++) {
			                BlockObj obj = obs[i];
			                BlockPos p = pos.add(new Vec3i(x, y, z));
			                worldObj.setBlockState(p, obj.getBlock().getStateFromMeta(obj.getMeta()));
										                
			                TileEntity ent = worldObj.getTileEntity(pos);
			                if(ent != null)ent.setPos(pos);
			                i++;
						}
					}
				}
				}catch(Throwable thr){
					
				}
			}
		}).start();
	}

	public World getWorld() {
		return worldObj;
	}
}
