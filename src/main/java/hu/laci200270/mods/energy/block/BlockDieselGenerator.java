package hu.laci200270.mods.energy.block;

import java.util.Collection;

import com.google.common.collect.ImmutableMap;

import hu.laci200270.mods.energy.tile.TileDieselGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDieselGenerator extends Block{
	private static final PropertyBool isRunning= PropertyBool.create("isRunning");
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
     @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn,
    		BlockPos pos) {
    	// TODO Auto-generated method stub
 //  TileDieselGenerator generator=(TileDieselGenerator) worldIn.getTileEntity(pos);
    	 //return state.withProperty(isRunning, generator.isRunning);
    	 return state;
    }
   /*  @Override
    protected BlockState createBlockState() {

    	return new BlockState(this, new IProperty[]{isRunning});
    }*/
     @Override
    public int getMetaFromState(IBlockState state) {
    	// TODO Auto-generated method stub
    	return 0;
    }
     
}
