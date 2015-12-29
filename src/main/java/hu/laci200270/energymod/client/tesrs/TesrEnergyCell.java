package hu.laci200270.energymod.client.tesrs;

import com.sun.javafx.geom.Quat4f;
import hu.laci200270.energymod.client.ClientHelper;
import javafx.scene.effect.Light;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3d;


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
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("energymod:textures/block/gradient.png"));
        Tessellator tessellator=Tessellator.getInstance();
        WorldRenderer worldRenderer=tessellator.getWorldRenderer();
        for(double theta = 0; theta < angle; theta += SUBDIVISIONS){

            worldRenderer.addVertexWithUV((float) Math.cos(theta), (float) Math.sin(theta), 0, 0, 0);
        }

        Tessellator.getInstance().draw();

        GlStateManager.popMatrix();
    }


}
