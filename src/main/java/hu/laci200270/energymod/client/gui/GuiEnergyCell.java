package hu.laci200270.energymod.client.gui;

import hu.laci200270.energymod.EnergyMod;
import hu.laci200270.energymod.common.gui.ContainerEnergyCell;
import hu.laci200270.energymod.common.tile.TileEnergyCell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * Created by laci200270 on 2015.11.28. at 18:Energy-mod .
 */
public class GuiEnergyCell extends GuiContainer {


    public GuiEnergyCell(Container inventorySlotsIn) {
        super(inventorySlotsIn);
        ContainerEnergyCell cont= (ContainerEnergyCell) inventorySlotsIn;
        TileEnergyCell cell=cont.tile;
        EnergyMod.logger.info("GUI open!");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawString(Minecraft.getMinecraft().fontRendererObj,"String",mouseX,mouseY,mouseX*mouseY);
    }
}
