package hu.laci200270.mods.energy.tile;

import hu.laci200270.mods.energy.fluid.FluidReference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileDieselGenerator extends TileEntity implements IFluidTank,
		IUpdatePlayerListBox {
	public int fuelAmount = 0;
	public int maxFuelAmount = 0;
	public int energyAmount = 0;
	public int maxEnergy = 20000;
	public static int fluxPerTick = 20;
	public static int milliBucketUsePerTick = 5;
	public boolean isRunning = false;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		fuelAmount = compound.getInteger("fuelAmount");
		fuelAmount = compound.getInteger("energyAmount");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setInteger("fuelAmount", fuelAmount);
		compound.setInteger("energyAmount", energyAmount);
	}

	@Override
	public FluidStack getFluid() {

		return new FluidStack(FluidReference.fluidDiesel, fuelAmount);
	}

	@Override
	public int getFluidAmount() {
		return fuelAmount;
	}

	@Override
	public int getCapacity() {
		
		return maxFuelAmount;
	}

	@Override
	public FluidTankInfo getInfo() {
		
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		
		int amount = resource.amount;
		int locFuelAmount = fuelAmount;
		int filled = 0;
		if (amount + fuelAmount > maxFuelAmount) {
			int overFlow = (amount + fuelAmount) - maxFuelAmount;
			filled = amount - overFlow;
		} else {
			locFuelAmount = amount + locFuelAmount;
			filled = amount;
		}
		if (doFill) {
			fuelAmount = locFuelAmount;
		}
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		int locFuelAmont = fuelAmount;
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
			fuelAmount = afterDrained;
		}
		return new FluidStack(this.getFluid(), afterDrained);
	}

	public TileDieselGenerator() {

	}

	@Override
	public void update() {

		if (fuelAmount - milliBucketUsePerTick < 0) {
			return;
		} else {
			if (energyAmount + fluxPerTick > fluxPerTick) {
				return;
			} else {
				fuelAmount = fuelAmount - milliBucketUsePerTick;
				energyAmount = energyAmount + fluxPerTick;
			}
		}
	}

}
