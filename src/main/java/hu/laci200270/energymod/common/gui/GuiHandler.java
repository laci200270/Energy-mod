package hu.laci200270.energymod.common.gui;

import hu.laci200270.energymod.EnergyMod;
import hu.laci200270.energymod.client.gui.GuiEnergyCell;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.util.LinkedHashMap;

/**
 * Created by laci200270 on 2015.11.28. at 18:Energy-mod .
 */
public class GuiHandler implements IGuiHandler {





    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return EnergyMod.guiRegistry.getServerGuiElement(ID,player,world,x,y,z);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return EnergyMod.guiRegistry.getClientGuiElement(ID,player,world,x,y,z);
    }

}
