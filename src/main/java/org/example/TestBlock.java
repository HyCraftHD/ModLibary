package org.example;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TestBlock extends Block implements ITileEntityProvider {

	public TestBlock() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		System.out.println("createTileEntity");
		return new TestTileEntity();
	}

}
