package hu.laci200270.energymod.waila;

import hu.laci200270.energymod.tile.TileEnergyConduit;
import mcp.mobius.waila.api.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

/**
 * @author laci200270
 * @date 2015.08.16.
 */
public class WailaProvider implements IWailaDataProvider {
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return null;
    }

    @Override
    public ITaggedList.ITipList getWailaHead(ItemStack itemStack, ITaggedList.ITipList iTipList, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return iTipList;
    }

    @Override
    public ITaggedList.ITipList getWailaBody(ItemStack itemStack, ITaggedList.ITipList currentTip, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        TileEntity tile=iWailaDataAccessor.getTileEntity();
        if(tile instanceof TileEnergyConduit){
            TileEnergyConduit tileEnergyConduit= (TileEnergyConduit) tile;
            currentTip.add(StatCollector.translateToLocal("text.energy")+tileEnergyConduit.energyAmount);
        }
        return currentTip;
    }

    @Override
    public ITaggedList.ITipList getWailaTail(ItemStack itemStack, ITaggedList.ITipList iTipList, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return iTipList;
    }

    @Override
    public NBTTagCompound getNBTData(TileEntity tileEntity, NBTTagCompound nbtTagCompound, IWailaDataAccessorServer iWailaDataAccessorServer) {
        return nbtTagCompound;
    }
}
