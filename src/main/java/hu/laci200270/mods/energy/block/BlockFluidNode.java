package hu.laci200270.mods.energy.block;

import com.google.common.base.Optional;

import hu.laci200270.mods.energy.tile.TileFluidNode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockFluidNode extends BlockContainer {

	protected BlockFluidNode() {
		super(Material.iron);
		setUnlocalizedName("fluidnode");

	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return new TileFluidNode();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
	                                IBlockState state, EntityPlayer playerIn, EnumFacing side,
	                                float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileFluidNode tile = (TileFluidNode) worldIn.getTileEntity(pos);
			BlockPos target = tile.findOutput().or(new BlockPos(0, 0, 0));
			;
			playerIn.addChatMessage(new ChatComponentText("Target position: "));
			playerIn.addChatMessage(new ChatComponentText("x: " + target.getX()));
			playerIn.addChatMessage(new ChatComponentText("y: " + target.getY()));
			playerIn.addChatMessage(new ChatComponentText("z: " + target.getZ()));
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileFluidNode();
	}
}
