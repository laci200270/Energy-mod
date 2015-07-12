package hu.laci200270.mods.energy.block;

import hu.laci200270.mods.energy.tile.TileFluidNode;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFluidNode extends Block {

	protected BlockFluidNode() {
		super(Material.iron);
	
	}
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return new TileFluidNode();
	}

}
