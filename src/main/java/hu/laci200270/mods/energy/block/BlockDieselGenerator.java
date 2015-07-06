package hu.laci200270.mods.energy.block;

import hu.laci200270.mods.energy.tile.TileDieselGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDieselGenerator extends Block{

	public static final String name="dieselGenerator";
	
	public BlockDieselGenerator() {
		super(Material.iron);
		
	}
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileDieselGenerator();
	}
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
	
		
		return -1;
	}
	 @Override
     public boolean isOpaqueCube() {
             return false;
     }
     
     //It's not a normal block, so you need this too.
     public boolean renderAsNormalBlock() {
             return false;
     }
     
}
