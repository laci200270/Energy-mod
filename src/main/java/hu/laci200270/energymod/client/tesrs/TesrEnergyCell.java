package hu.laci200270.energymod.client.tesrs;

import hu.laci200270.energymod.client.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


/**
 * Created by Laci on 2015. 12. 27..
 */
public class TesrEnergyCell extends TileEntitySpecialRenderer {


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {









        GlStateManager.pushMatrix();
        Tessellator.getInstance().getWorldRenderer().startDrawing(GL11.GL_TRIANGLE_FAN);
        GL11.glTranslated(x+0.5F, y + 1.25F, z+0.5F);
        Tessellator.getInstance().getWorldRenderer().setColorRGBA(255, 255, 0, 128);
        ClientHelper.setRotation(te);
        GlStateManager.rotate(330,1,0,0);
        GlStateManager.scale(0.25F,0.25F,0.25F);


        double angle=Math.toRadians(180);
        double SUBDIVISIONS=0.017;
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.color(1, 1, 1, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("energymod:textures/block/gradient.png"));

        Tessellator tessellator=Tessellator.getInstance();
        WorldRenderer worldRenderer=tessellator.getWorldRenderer();
        worldRenderer.addVertexWithUV(0,0,0,1,1);
        for(double theta = 0; theta < angle; theta += SUBDIVISIONS){

            worldRenderer.addVertexWithUV((float) Math.cos(theta), (float) Math.sin(theta), 0, 0, 0);
        }

        Tessellator.getInstance().draw();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }


}
