package hu.laci200270.energymod.common.tile;

import cofh.api.energy.IEnergyReceiver;
import hu.laci200270.energymod.common.enums.EnumSideMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Laci on 2016. 01. 01..
 */
public class TileFurnance extends TileEntity implements IInventory,ITickable,IEnergyReceiver{
    ItemStack[] inventory=new ItemStack[2];

    int remainingTicks=-1;
    ItemStack resultAfterBurning=null;
    int totalBurningTicks=200;
    private int energyAmount;

    public TileFurnance(){
        inventory[0]=null;
        inventory[1]=null;
    }
    public static String stringInvCompoundName="slot%d";
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        for(int i=0;i<inventory.length;i++){
            inventory[i]=ItemStack.loadItemStackFromNBT(compound.getCompoundTag(String.format(stringInvCompoundName,i)));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        for(int i=0;i<inventory.length;i++){
            if(inventory[i]!=null){
                inventory[i].writeToNBT(compound.getCompoundTag(stringInvCompoundName));
            }        }
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(inventory[index]!=null){
            inventory[index].stackSize--;
            if(inventory[index].stackSize==0)
                inventory[index]=null;
        }
        return inventory[index];
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack=inventory[index].copy();
        inventory[index]=null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory[index]=stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
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
        return true;
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
    public void update() {
        ItemStack result=FurnaceRecipes.instance().getSmeltingResult(inventory[0]);
        if(remainingTicks==-1){
        if(result!=null){
            if((inventory[1]!=null&&result.getItem().equals(inventory[1].getItem()))||inventory[1]==null){
               if(result.stackSize+inventory[1].stackSize>result.getMaxStackSize())
                if(this.energyAmount>=200){
                    resultAfterBurning=
                }
            }
        }
        }
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

    public boolean isBurning() {
        return burning;
    }

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {


            int received = 0;

                if (maxReceive + energyAmount < getMaxEnergyStored(EnumFacing.UP)) {
                    received = maxReceive;
                } else {
                    int virtualOverflow = energyAmount + maxReceive;
                    int variable2 = virtualOverflow - maxReceive; //I had no better idea for variable name :(
                    received = getMaxEnergyStored(EnumFacing.UP) - variable2;
                }
                if (!simulate)
                    this.energyAmount += received;
                worldObj.markBlockForUpdate(pos);
                markDirty();

            return received;



    }

    @Override
    public int getEnergyStored(EnumFacing facing) {
        return energyAmount;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {
        return 12000;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {
        return true;
    }
}
