package hu.laci200270.energymod.tile;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * @author laci200270
 * @date 2015.08.16.
 */
public class TileEnergyConduit extends TileEntity implements IEnergyHandler,IUpdatePlayerListBox{

    public int energyAmount=0;


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        energyAmount=compound.getInteger("energy");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("energy",energyAmount);
    }

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {
        return false;
    }

    @Override
    public void update() {
        for (EnumFacing facing:EnumFacing.VALUES){
            BlockPos targetpos=getPos().offset(facing);
            IBlockState state=worldObj.getBlockState(targetpos);
            if(state.getBlock().hasTileEntity(state)){
                TileEntity targeTE=worldObj.getTileEntity(targetpos);
                if(targeTE instanceof IEnergyReceiver){
                    IEnergyReceiver receiver= (IEnergyReceiver) targeTE;
                    if(targeTE instanceof TileEnergyConduit){
                        TileEnergyConduit condouit= (TileEnergyConduit) targeTE;
                        int received=condouit.receiveEnergy(facing.getOpposite(),this.energyAmount,true);
                        this.energyAmount=this.energyAmount-received;
                        condouit.receiveEnergy(facing.getOpposite(),received,false);
                    }
                    else{
                        int received=receiver.receiveEnergy(facing.getOpposite(),calculateMaxOutPut(200),true);
                        this.energyAmount=this.energyAmount-received;
                        receiver.receiveEnergy(facing.getOpposite(),received,false);
                    }
                }
            }
        }
    }

private int calculateMaxOutPut(int rate){
    if(this.energyAmount>rate){
        return rate;
    }
    else
        return energyAmount;
}
}