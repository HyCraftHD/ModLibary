package net.hycrafthd.corelib.util.cschematic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import net.hycrafthd.corelib.util.BlockUtil;
import net.hycrafthd.corelib.util.NBTUtil;
import net.hycrafthd.corelib.util.process.IProcess;
import net.hycrafthd.corelib.util.process.ProcessHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

class RegionSaver implements IProcess {

	private World world;
	private SchematicBoundingBox box;
	private File file;
	private HashMap<Integer, Layer> layers = new HashMap<Integer, Layer>();
	private boolean call = false;
	private boolean isDead = false;

	public RegionSaver(World world, SchematicBoundingBox box, File file) {
		this.world = world;
		this.box = box;
		this.file = file;
		this.isDead = world.isRemote;
	}

	@Override
	public void updateProcess() {
		int xSize = box.getXSize();
		int ySize = box.getYSize();
		int zSize = box.getZSize();
		int gSize = xSize * ySize * zSize;

		if (!call) {

			System.out.println("call");

			for (int y = box.minY; y <= box.maxY; ++y) {
				Layer layer = new Layer(world, y, box.getMinPos(), box.getMaxPos());
				ProcessHandler.addProcess(layer);
				layers.put(y, layer);
			}
			call = true;
		}

		if (isAllReady()) {

			System.out.println("ready");

			NBTTagCompound tag = new NBTTagCompound();
			NBTTagCompound blocks = new NBTTagCompound();
			for (Entry<Integer, Layer> entry : layers.entrySet()) {
				blocks.setTag("layer-" + entry.getKey(), entry.getValue().getList());
			}
			tag.setInteger("xSize", xSize);
			tag.setInteger("ySize", ySize);
			tag.setInteger("zSize", zSize);
			tag.setInteger("gSize", gSize);
			tag.setTag("blocks", blocks);

			try {
				NBTUtil.writeNBTToFile(tag, file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			isDead = true;
		}
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	private boolean isAllReady() {
		for (Layer layer : layers.values()) {
			if (!layer.isDead()) {
				return false;
			}
		}
		return true;
	}

	class Layer implements IProcess {

		private World world;
		private int y;
		private BlockPos pos1;
		private BlockPos pos2;
		private NBTTagList list;
		private boolean call = false;
		private boolean isDead = false;

		Layer(World world, int y, BlockPos pos1, BlockPos pos2) {
			this.world = world;
			this.y = y;
			this.pos1 = pos1;
			this.pos2 = pos2;
		}

		@Override
		public void updateProcess() {

			if (!this.call) {

				this.list = new NBTTagList();

				for (int x = this.pos1.getX(); x <= this.pos2.getX(); x++) {
					for (int z = this.pos1.getZ(); z <= this.pos2.getZ(); z++) {

						NBTTagCompound blocktag = new NBTTagCompound();

						BlockPos pos = new BlockPos(x, this.y, z);

						IBlockState state = this.world.getBlockState(pos);
						Block block = state.getBlock();

						blocktag.setString("id", String.valueOf(Block.blockRegistry.getNameForObject(block)));
						blocktag.setInteger("meta", block.getMetaFromState(state));

						TileEntity tileentity = BlockUtil.getTileEntity(this.world, pos);

						if (tileentity != null) {

							NBTTagCompound tileentitytag = new NBTTagCompound();

							tileentity.writeToNBT(tileentitytag);

							tileentitytag.removeTag("x");
							tileentitytag.removeTag("y");
							tileentitytag.removeTag("z");

							blocktag.setTag("tileentity", tileentitytag);
						}

						this.list.appendTag(blocktag);
					}
				}
				this.call = true;
			} else {
				this.isDead = true;
			}
		}

		public NBTTagList getList() {
			return this.list;
		}

		@Override
		public boolean isDead() {
			return this.isDead;
		}
	}

}
