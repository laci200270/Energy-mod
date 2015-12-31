package hu.laci200270.energymod.client.tesrs;

import hu.laci200270.energymod.client.ClientHelper;
import hu.laci200270.energymod.common.tile.TileEnergyCell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.opengl.GL11;


/**
 * Created by Laci on 2015. 12. 27..
 */
public class TesrEnergyCell extends TileEntitySpecialRenderer {


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        TileEnergyCell eCell = (TileEnergyCell) te;

        double energyStored = eCell.getEnergyStored(EnumFacing.UP);
        double maxEnergyStored = eCell.getMaxEnergyStored(EnumFacing.UP);
        double percentage = energyStored / maxEnergyStored;

        ClientHelper.renderCircle(new ClientHelper.ColorRGBA(0,1,0,0),new ClientHelper.ColorRGBA(0,1,0,1),360*percentage,new ClientHelper.Translation3d(x,y,z),new ClientHelper.Translation3d(0.5,1.25,0.5));


    }


}
