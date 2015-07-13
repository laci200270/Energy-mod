package hu.laci200270.mods.energy.tile;

import hu.laci200270.mods.energy.block.BlockReference;
import hu.laci200270.mods.energy.dummyclasses.DummyBlockStore;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

import com.google.common.collect.Lists;

public class TileFluidNode extends TileEntity implements IFluidTank,IUpdatePlayerListBox {

	public int fluidAmount = 0;
	public int maxFluidAmount = 0;
	private FluidStack fluid=null;

	

	

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		
		fluid=FluidStack.loadFluidStackFromNBT(compound.getCompoundTag("fluid"));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		if(fluid!=null){
			fluid.writeToNBT(compound.getCompoundTag("fluid"));
		}
		
	}

	@Override
	public FluidStack getFluid() {

		return fluid;
	}

	@Override
	public int getFluidAmount() {
		return fluidAmount;
	}

	@Override
	public int getCapacity() {
		
		return maxFluidAmount;
	}

	@Override
	public FluidTankInfo getInfo() {
	
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		
		int amount = resource.amount;
		int locFuelAmount = fluidAmount;
		int filled = 0;
		if (amount + fluidAmount > maxFluidAmount) {
			int overFlow = (amount + fluidAmount) - maxFluidAmount;
			filled = amount - overFlow;
		} else {
			locFuelAmount = amount + locFuelAmount;
			filled = amount;
		}
		if (doFill) {
			fluidAmount = locFuelAmount;
		}
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		
		int locFuelAmont = fluidAmount;
		int afterDrained = 0;
		int drained = 0;
		if (locFuelAmont - maxDrain < 0) {
			int underFlow = maxDrain - locFuelAmont;
			drained = maxDrain - underFlow;
		} else {
			drained = maxDrain;
		}
		afterDrained = locFuelAmont - drained;
		if (doDrain) {
			fluidAmount = afterDrained;
		}
		return new FluidStack(this.getFluid(), afterDrained);
	}

	@Override
	public void update() {
		ArrayList<BlockPos> scannedPos=new ArrayList<BlockPos>();
		
		
		
	}

	public ArrayList<DummyBlockStore> getNeighbourBlocks(BlockPos pos,World world){
		ArrayList<DummyBlockStore> returnable=new ArrayList<DummyBlockStore>();
		
		for (int i = 0; i < EnumFacing.VALUES.length; i++) {
			BlockPos position=pos.offset(EnumFacing.VALUES[i]);
			returnable.add(new DummyBlockStore(position,world.getBlockState(position).getBlock()));
		}
		
		return returnable;
		
	}
	public List<BlockPos> getNeighborPipeBlocks(BlockPos pos, World world) {
	    List<BlockPos> result = Lists.newArrayList();
	    for (int i = 0; i < EnumFacing.VALUES.length; i++) {
	        BlockPos p1 = pos.offset(EnumFacing.VALUES[i]);
	        if (world.getBlockState(p1).getBlock() == BlockReference.fluidPipe)
	            result.add(p1);
	    }
	    return result;
	}
	
	@SuppressWarnings("deprecation")
	public List<IFluidTank> getNearTanks(BlockPos pos,World world){
		List<IFluidTank> result = Lists.newArrayList();
		for (int i = 0; i < EnumFacing.VALUES.length; i++) {
	        BlockPos p1 = pos.offset(EnumFacing.VALUES[i]);
	        IBlockState blockState=world.getBlockState(p1);
	        Block block=blockState.getBlock();
	        if(block.hasTileEntity()||block.hasTileEntity(blockState)){
	        	if(p1!=getPos()){
	        	if(world.getTileEntity(p1) instanceof IFluidTank){
	        		result.add((IFluidTank) world.getTileEntity(p1));
	        	}
	        }}
	    }
		return result;
	}
	
	public List<BlockPos> discoverPipes(List<BlockPos> scanned, BlockPos pos) {
	    scanned.add(pos);
	    for (BlockPos p : getNeighborPipeBlocks(pos, worldObj))
	        if (!scanned.contains(p))
	            discoverPipes(scanned, pos);
	    return scanned;
	}
	
	public List<IFluidTank> getTanksNearToThePipes(List<BlockPos> pipes,World world){
		List<IFluidTank> result = Lists.newArrayList();
		for (BlockPos pipe : pipes) {
			List<IFluidTank> tanks=getNearTanks(pipe, world);
			for (IFluidTank iFluidTank : tanks) {
				if(!result.contains(iFluidTank)){
					result.add(iFluidTank);
				}
			}
		}
		return result;
	}
}
