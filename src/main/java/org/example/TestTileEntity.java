package org.example;

import net.hycrafthd.core.tileentity.ICustomTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TestTileEntity extends TileEntity implements ICustomTileEntity {

	@Override
	public String getRegisterName() {
		return "testtileentity";
	}

}
