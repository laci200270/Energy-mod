package hu.laci200270.mods.energy.tile;

import hu.laci200270.mods.energy.block.BlockUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class TileFluidNode extends TileEntity implements IFluidTank, IUpdatePlayerListBox, FluidOutput {

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

	
	public List<BlockPos> getNeighborPipeBlocks(BlockPos pos, World world) {
		List<BlockPos> result = Lists.newArrayList();
		for (EnumFacing facing:EnumFacing.VALUES) {
			BlockPos p1 = pos.offset(facing);
			if (BlockUtil.isPipe(world.getBlockState(p1).getBlock())) {
				result.add(p1);
			}
		}
		return result;
	}
	 
	public Optional<BlockPos> findOutput(BlockPos pos, World world) {
		// NOTE: If you ever add teserects, you'll need to track BlockPos-World pairs.
		LinkedList<BlockPos> queue = Lists.newLinkedList();
		List<BlockPos> checked = Lists.newArrayList();
		queue.add(pos);
		while (!queue.isEmpty()) {
			BlockPos current = queue.pop();
			
			TileEntity te = world.getTileEntity(current);
			if (current != pos && te instanceof FluidOutput) {
				if (canOutput(current, world))
					return Optional.of(current);
			}
			checked.add(current);
			for (BlockPos p : getNeighborPipeBlocks(current, world)) {
				if (!checked.contains(p))
					queue.add(p);
			}
		}
		
		return Optional.absent();
		
	}

	public Optional<IFluidTank> getTankIfAdjacent(BlockPos pos, World world) {
		for(EnumFacing facing : EnumFacing.VALUES) {
			BlockPos onFace = pos.offset(facing);

			TileEntity te = world.getTileEntity(onFace);
			if (te != null && te instanceof IFluidTank) {
				return Optional.of((IFluidTank) te);
			}
		}

		return Optional.absent();
	}

	@Override
	public boolean canOutput(BlockPos pos, World world) {
		return getTankIfAdjacent(pos, world).isPresent();
	}
}
