package hu.laci200270.energymod.client.tesrs;

import com.sun.javafx.geom.Quat4f;
import hu.laci200270.energymod.client.ClientHelper;
import javafx.scene.effect.Light;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
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







        GL11.glPushMatrix();

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("miencraft:diamond_block"));
        Tessellator.getInstance().getWorldRenderer().startDrawing(GL11.GL_TRIANGLE_FAN);
        GL11.glTranslated(x+0.5F, y + 2F, z+0.5F);
        Tessellator.getInstance().getWorldRenderer().setColorRGBA(255, 255, 0, 128);
        new ClientHelper().setRotation(te);
        int NUM_PIZZA_SLICES = 200;
        for (int i = 0; i <= NUM_PIZZA_SLICES; i++) { //NUM_PIZZA_SLICES decides how round the circle looks.
            double SUBDIVISIONS = 180;
            double angle = Math.PI * 2 * i / SUBDIVISIONS;
            //GL11.glVertex2f((float) Math.cos(angle), (float) Math.sin(angle));
            Tessellator.getInstance().getWorldRenderer().addVertex((float) Math.cos(angle), (float) Math.sin(angle), 0);
        }

        Tessellator.getInstance().draw();

        GL11.glPopMatrix();
    }


}
