package hu.laci200270.energymod.common.tile;

import cofh.api.energy.IEnergyProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.IFuelHandler;

/**
 * @author laci200270
 * @date 2015.08.16.
 */
public class TileGenerator extends TileEntity implements IEnergyProvider, ITickable, IInventory {

    public int energyAmount = 0;

    public int maxEnergy = 12000000;

    private ItemStack[] inv;


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        energyAmount = compound.getInteger("energy");
        inv[0] = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("inventory").getCompoundTag("fuel"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("energy", energyAmount);
        inv[0].writeToNBT(compound.getCompoundTag("inventory").getCompoundTag("fuel"));
    }


    @Override
    public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate) {
        int rate= calculateMaxOutPut(Math.min(maxExtract,1500));
        if(!simulate)
            energyAmount-=rate;
        return  rate;
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



        }


    }

    private int calculateMaxOutPut(int rate) {
        if (this.energyAmount > rate) {
            return rate;
        } else
            return rate;
    }


    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inv[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        int currentStackSize = inv[index].stackSize;
        if (currentStackSize - count >= 0)
            inv[index] = null;
        else {
            int newStackSize = currentStackSize - count;
            inv[index].stackSize = newStackSize;
        }


        return inv[index];
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        inv[index] = null;
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inv[index] = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 16;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return stack.getItem() instanceof IFuelHandler;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }


}
