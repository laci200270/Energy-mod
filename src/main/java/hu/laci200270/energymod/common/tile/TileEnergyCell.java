package hu.laci200270.energymod.common.tile;

import cofh.api.energy.IEnergyHandler;
import hu.laci200270.energymod.common.enums.EnumSideMode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.util.HashMap;

/**
 * Created by laci200270 on 2015.11.22. at 16:Energy-mod .
 */
public class TileEnergyCell extends TileEntity implements IEnergyHandler, IUpdatePlayerListBox {

    HashMap<EnumFacing, EnumSideMode> sides = new HashMap<EnumFacing, EnumSideMode>();
    private int energyAmount = 0;
    private int maxEnergy = 20000;

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
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("down", sides.get("down").ordinal());
        compound.setInteger("up", sides.get("up").ordinal());
        compound.setInteger("east", sides.get("east").ordinal());
        compound.setInteger("west", sides.get("west").ordinal());
        compound.setInteger("north", sides.get("north").ordinal());
        compound.setInteger("south", sides.get("south").ordinal());
        compound.setInteger("energy", energyAmount);
    }

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
        int received = 0;
        if (sides.get(facing)!=null&&sides.get(facing).equals(EnumSideMode.INPUT)) {
            if (maxReceive + energyAmount > maxEnergy) {
                received = energyAmount + maxReceive - maxReceive;
            } else
                received = energyAmount;
            if (simulate)
                this.energyAmount += received;
        }
        return received;
    }

    @Override
    public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
        return 0;
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
}
