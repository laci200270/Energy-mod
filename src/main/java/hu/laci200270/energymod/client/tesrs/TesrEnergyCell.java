package hu.laci200270.energymod.client.tesrs;

import hu.laci200270.energymod.common.tile.TileEnergyCell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
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


        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y + 1.25, z + 0.5);
        GlStateManager.rotate(180 - Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(0.25, 0.25, 0.25);

        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);

        WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
        renderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos(0, 0, 0).color(0, 1f, 0, 0f).endVertex();
        double targetAngle = 360 * percentage;
        if (targetAngle > 360)
            targetAngle = 360;
        double angle2 = Math.toRadians(targetAngle);
        for (double theta = 0; theta <= angle2; theta += (2 * Math.PI / 360)) {
            renderer.pos(Math.cos(theta), Math.sin(theta), 0).color(0f, 1f, 0f, 1f).endVertex();
        }
        Tessellator.getInstance().draw();

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

    }


}
