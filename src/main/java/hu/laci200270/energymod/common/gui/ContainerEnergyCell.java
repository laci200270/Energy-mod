package hu.laci200270.energymod.common.gui;

import hu.laci200270.energymod.common.tile.TileEnergyCell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by laci200270 on 2015.11.28. at 18:Energy-mod .
 */
public class ContainerEnergyCell extends Container {


    public TileEnergyCell  tile;

    public ContainerEnergyCell(TileEntity tile){
        this.tile= (TileEnergyCell) tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
