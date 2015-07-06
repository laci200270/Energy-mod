package hu.laci200270.mods.energy.tile;

import hu.laci200270.mods.energy.fluid.FluidReference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileDieselGenerator extends TileEntity implements IFluidTank {
	public int fuelAmount;
	public int maxFuelAmount;
	public int energyAmount;
	public int maxEnergy;
	
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		fuelAmount=compound.getInteger("fuelAmount");
		fuelAmount=compound.getInteger("energyAmount");
	}
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setInteger("fuelAmount", fuelAmount);
		compound.setInteger("energyAmount",energyAmount);
	}
	@Override
	public FluidStack getFluid() {
		
		return new FluidStack(FluidReference.fluidDiesel,fuelAmount);
	}
	@Override
	public int getFluidAmount() {
		// TODO Auto-generated method stub
		return fuelAmount;
	}
	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 20000;
	}
	@Override
	public FluidTankInfo getInfo() {
		// TODO Auto-generated method stub
		return new FluidTankInfo(this);
	}
	@Override
	public int fill(FluidStack resource, boolean doFill) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
