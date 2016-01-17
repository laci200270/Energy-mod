package hu.laci200270.energymod.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;

/**
 * Created by Laci on 2016. 01. 01..
 */
public class TileFurnance extends TileEntity implements IInventory,ITickable{
    ItemStack[] inventory=new ItemStack[2];

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
