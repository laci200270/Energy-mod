package hu.laci200270.energymod.client.tesrs;

import hu.laci200270.energymod.client.ClientHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Laci on 2015. 12. 31..
 */
public class TesrGenerator extends TileEntitySpecialRenderer{
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        ClientHelper.renderCircle(new ClientHelper.ColorRGBA(1,0,0,0),new ClientHelper.ColorRGBA(1,0,0,1),180,new ClientHelper.Translation3d(x,y,z),new ClientHelper.Translation3d(0.5,1.25,0.5),new ClientHelper.Scaling3D(0.125,0.125,0.125));
    }
}
