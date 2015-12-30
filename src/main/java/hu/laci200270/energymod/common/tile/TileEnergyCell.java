package hu.laci200270.energymod.common.tile;

import cofh.api.energy.IEnergyHandler;
import hu.laci200270.energymod.common.enums.EnumSideMode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import java.util.HashMap;

/**
 * Created by laci200270 on 2015.11.22. at 16:Energy-mod .
 */
public class TileEnergyCell extends TileEntity implements IEnergyHandler, ITickable {

    HashMap<EnumFacing, EnumSideMode> sides = new HashMap<EnumFacing, EnumSideMode>();
    private int energyAmount = 0;
    private int maxEnergy = 20000;


    public TileEnergyCell() {
        for (EnumFacing current : EnumFacing.HORIZONTALS) {
            sides.put(current, EnumSideMode.INPUT);
        }
        sides.put(EnumFacing.DOWN, EnumSideMode.OUTPUT);
        sides.put(EnumFacing.UP, EnumSideMode.INPUT);
    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        sides.put(EnumFacing.DOWN, EnumSideMode.values()[compound.getInteger("down")]);
        sides.put(EnumFacing.UP, EnumSideMode.values()[compound.getInteger("up")]);
        sides.put(EnumFacing.EAST, EnumSideMode.values()[compound.getInteger("east")]);
        sides.put(EnumFacing.WEST, EnumSideMode.values()[compound.getInteger("west")]);
        sides.put(EnumFacing.NORTH, EnumSideMode.values()[compound.getInteger("north")]);
        sides.put(EnumFacing.SOUTH, EnumSideMode.values()[compound.getInteger("south")]);
        energyAmount = compound.getInteger("energy");

        if(energyAmount>maxEnergy)
            energyAmount=maxEnergy;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("down", sides.get(EnumFacing.DOWN).ordinal());
        compound.setInteger("up", sides.get(EnumFacing.UP).ordinal());
        compound.setInteger("east", sides.get(EnumFacing.EAST).ordinal());
        compound.setInteger("west", sides.get(EnumFacing.WEST).ordinal());
        compound.setInteger("north", sides.get(EnumFacing.NORTH).ordinal());
        compound.setInteger("south", sides.get(EnumFacing.SOUTH).ordinal());
        compound.setInteger("energy", energyAmount);

    }

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
        int received = 0;
        if (sides.get(facing) == EnumSideMode.INPUT) {
            if (maxReceive + energyAmount < maxEnergy) {
                received = maxReceive;
            } else {
                int virtualoverflow=energyAmount+maxReceive;
                int variable2=virtualoverflow-maxReceive; //I had no better idea :(
                received=maxEnergy-variable2;


            }
            if (!simulate)
                this.energyAmount += received;
        }
        return received;
    }

    @Override
    public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
        int extracted = calculateMaxOutPut(maxExtract);
        if (!simulate) {
            energyAmount = energyAmount - extracted;
        }
        return extracted;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {
        return energyAmount;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {
        return maxEnergy;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {
        return sides.get(facing) == EnumSideMode.INPUT || sides.get(facing) == EnumSideMode.OUTPUT;


    }

    @Override
    public void update() {

    }

    private int calculateMaxOutPut(int rate) {
        if (this.energyAmount > rate) {
            return rate;
        } else
            return energyAmount;
    }


}
