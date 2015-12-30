package hu.laci200270.energymod.common.tile;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import hu.laci200270.energymod.EnergyMod;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

/**
 * @author laci200270
 * @date 2015.08.16.
 */
public class TileEnergyConduit extends TileEntity implements IEnergyHandler, ITickable {

    public int energyAmount = 0;
    public int maxEnergy = 1200;


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        energyAmount = compound.getInteger("energy");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("energy", energyAmount);
    }

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
        int received = 0;
        if (maxReceive + energyAmount > maxEnergy) {
            received = energyAmount + maxReceive - maxReceive;
        } else
            received = energyAmount;
        if (simulate)
            this.energyAmount += received;
        return received;
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
        if (!worldObj.isRemote) {


            Set<BlockPos> connectedPipes = scanForPipes(getPos(), worldObj, new HashSet<BlockPos>());
            Set<TransferObject> receivers = findAllReceivers(worldObj, connectedPipes, new HashSet<TransferObject>());
            for (TransferObject tobject : receivers) {
                int maxPullableEnergy = 0;
                Set<EnumFacing> neighbourEnergyProviders = findNeighbourProviders(worldObj, pos);
                for (EnumFacing facing : neighbourEnergyProviders) {
                    BlockPos targetPos = pos.offset(facing);
                    IEnergyProvider provider = (IEnergyProvider) worldObj.getTileEntity(targetPos);
                    maxPullableEnergy += provider.extractEnergy(facing.getOpposite(), 200, true);
                }
                int inserted = tobject.receiver.receiveEnergy(tobject.where, maxPullableEnergy, true);
                int remain = inserted;
                while (remain != 0) {
                    for (EnumFacing facing : neighbourEnergyProviders) {
                        if (remain > 0) {
                            BlockPos targetPos = pos.offset(facing);
                            IEnergyProvider provider = (IEnergyProvider) worldObj.getTileEntity(targetPos);
                            int pulled = provider.extractEnergy(facing.getOpposite(), 1, true);
                            if (pulled == 1) {
                                provider.extractEnergy(facing.getOpposite(), 1, false);
                                remain--;
                            }
                        }

                    }
                }

                tobject.receiver.receiveEnergy(tobject.where, inserted, false);
            }

        }


    }

    private int calculateMaxOutPut(int rate) {
        if (this.energyAmount > rate) {
            return rate;
        } else
            return rate;
    }

    Set<BlockPos> scanForPipes(BlockPos startPos, World world, Set<BlockPos> preScanned) {
        for (EnumFacing currentFacing : EnumFacing.VALUES) {
            BlockPos pos = startPos.offset(currentFacing);
            if (world.getBlockState(pos).getBlock() == EnergyMod.conduitEnergy && !(preScanned.contains(pos))) {
                preScanned.add(pos);
                scanForPipes(pos, world, preScanned);
            }
        }
        return preScanned;
    }

    Set<TransferObject> findAllReceivers(World world, Set<BlockPos> positions, Set<TransferObject> previousResults) {
        for (BlockPos currentPos : positions) {
            for (EnumFacing offset : EnumFacing.VALUES) {
                BlockPos offsetPos = currentPos.offset(offset);
                if (!(world.getBlockState(offsetPos).getBlock() == EnergyMod.conduitEnergy) && world.getTileEntity(offsetPos) != null & world.getTileEntity(offsetPos) instanceof IEnergyHandler && !(previousResults.contains((IEnergyHandler) world.getTileEntity(offsetPos)))) {
                    previousResults.add(new TransferObject(offset.getOpposite(), (IEnergyReceiver) world.getTileEntity(offsetPos)));
                }

            }

        }
        return previousResults;
    }

    Set<EnumFacing> findNeighbourProviders(World world, BlockPos currentPos) {
        Set<EnumFacing> returanble = new HashSet<EnumFacing>();

        for (EnumFacing currentOffset : EnumFacing.VALUES) {
            BlockPos offsetPos = currentPos.offset(currentOffset);
            if (!(world.getBlockState(offsetPos).getBlock() == EnergyMod.conduitEnergy) && world.getTileEntity(offsetPos) != null & world.getTileEntity(offsetPos) instanceof IEnergyProvider) {
                returanble.add(currentOffset);
            }
        }

        return returanble;
    }

    private class TransferObject {
        public EnumFacing where;
        public IEnergyReceiver receiver;

        public TransferObject(EnumFacing where, IEnergyReceiver receiver) {
            this.where = where;
            this.receiver = receiver;
        }
    }
}