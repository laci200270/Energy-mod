package hu.laci200270.mods.energy.tile;

import hu.laci200270.mods.energy.block.BlockReference;
import hu.laci200270.mods.energy.dummyclasses.DummyBlockStore;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

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
	public ArrayList<BlockPos> getNeighbourPipeBlocks(BlockPos pos,World world){
		ArrayList<BlockPos> returnable=new ArrayList<BlockPos>();
		
		for (int i = 0; i < EnumFacing.VALUES.length; i++) {
			BlockPos position=pos.offset(EnumFacing.VALUES[i]);
			if(world.getBlockState(pos.offset(EnumFacing.VALUES[i])).getBlock()==BlockReference.fluidPipe){
				returnable.add(pos.offset(EnumFacing.VALUES[i]));
			}
			
		}
		
		return returnable;
		
	}
	public ArrayList<BlockPos> getPipeBlocks(BlockPos pos){
		ArrayList<BlockPos> returnable=new ArrayList<BlockPos>();
		ArrayList<BlockPos> scannedPos=new ArrayList<BlockPos>();
		
		
		
		return returnable;
		
	}
	public ArrayList<BlockPos> discoverPipes(ArrayList<BlockPos> alreadyScanned,BlockPos pos){
		
		
		return alreadyScanned;
	}
	
}
