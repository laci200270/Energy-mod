package hu.laci200270.mods.energy.block;

import hu.laci200270.mods.energy.tile.TileDieselGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.client.model.b3d.B3DModel.Animation;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class BlockDieselGenerator extends BlockContainer {
	private static final PropertyBool isRunning = PropertyBool
			.create("isRunning");
	public static final String name = "dieselGenerator";

	private int counter = 1;
	private ExtendedBlockState state = new ExtendedBlockState(this,
			new IProperty[0],
			new IUnlistedProperty[] { B3DLoader.B3DFrameProperty.instance });

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
/*
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		//return false;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return false;
	}
*/
	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world,
			BlockPos pos) {

		B3DLoader.B3DState newState = new B3DLoader.B3DState(null, counter);
		return ((IExtendedBlockState) this.state.getBaseState()).withProperty(
				B3DLoader.B3DFrameProperty.instance, newState);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos,
			IBlockState state, EntityPlayer player, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			System.out.println("click " + counter);
			if (player.isSneaking())
				counter--;
			else
				counter++;
			// if(counter >= model.getNode().getKeys().size()) counter = 0;
			world.markBlockRangeForRenderUpdate(pos, pos);
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileDieselGenerator();
	}

}
