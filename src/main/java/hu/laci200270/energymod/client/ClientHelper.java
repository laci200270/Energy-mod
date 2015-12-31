package hu.laci200270.energymod.client;

import hu.laci200270.energymod.EnergyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Vector3d;

/**
 * Created by Laci on 2015. 12. 28..
 */
public class ClientHelper {

    int gradientId = 0;

    public static void setRotation(TileEntity tileEntity) {
        Vector3d teLoc = new Vector3d(tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ());
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        Vector3d playerLoc = new Vector3d();
        playerLoc.setX(player.posX);
        playerLoc.setY(player.posY + player.getEyeHeight());
        playerLoc.setZ(player.posZ);
        Vector3d lookVec = new Vector3d(playerLoc.getX() - teLoc.getX(), playerLoc.getY() - teLoc.getY(), playerLoc.getZ() - teLoc.getZ());
        double angleYaw = Math.atan2(lookVec.getZ(), lookVec.getX()) - Math.PI / 2d;
        double anglePitch = Math.atan2(lookVec.getY(), Math.sqrt(lookVec.getX() * lookVec.getX() + lookVec.getZ() * lookVec.getZ()));
        GlStateManager.rotate(-(float) Math.toDegrees(angleYaw), 0, 1, 0);

    }

    public static void renderCircle(ColorRGBA inside,ColorRGBA outside,double angle,Translation3d pos,Translation3d trans,Scaling3D scaling3D){
        GlStateManager.pushMatrix();
        GlStateManager.translate(pos.getX() + trans.getX(), pos.getY() + trans.getY(), pos.getZ() + trans.getZ());
        GlStateManager.rotate(180 - Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(scaling3D.getX(), scaling3D.getY(), scaling3D.getZ());
        EnergyMod.logger.info("Rendering at "+pos.getX()+";"+pos.getY()+";"+pos.getZ());
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);

        WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
        renderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos(0, 0, 0).color(inside.red,inside.green,inside.blue,inside.alpha).endVertex();

        double angle2 = Math.toRadians(angle);
        for (double theta = 0; theta <= angle2; theta += (2 * Math.PI / 360)) {
            renderer.pos(Math.cos(theta), Math.sin(theta), 0).color(inside.red,inside.green,inside.blue,inside.alpha).endVertex();
        }
        Tessellator.getInstance().draw();

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void renderCircle(ColorRGBA inside,ColorRGBA outside,double angle,Translation3d pos,Translation3d trans){
        renderCircle(inside,outside,angle,pos,trans,new Scaling3D(0.25,0.25,025));
    }

    public static class ColorRGBA {
         
        public float red;
        public float green;
        public float blue;
        public float alpha;
        public ColorRGBA(float red, float green, float blue,float alpha) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }


    }

    public static class Translation3d {
        private double X;
        private double Y;
        private double Z;

        public Translation3d(double x, double y, double z) {
            X = x;
            Y = y;
            Z = z;
        }

        public double getX() {
            return X;
        }

        public void setX(double x) {
            X = x;
        }

        public double getY() {
            return Y;
        }

        public void setY(double y) {
            Y = y;
        }

        public double getZ() {
            return Z;
        }

        public void setZ(double z) {
            Z = z;
        }
    }

    public static class Scaling3D {
        private double X;
        private double Y;
        private double Z;

        public Scaling3D(double x, double y, double z) {
            X = x;
            Y = y;
            Z = z;
        }

        public double getX() {
            return X;
        }

        public void setX(double x) {
            X = x;
        }

        public double getY() {
            return Y;
        }

        public void setY(double y) {
            Y = y;
        }

        public double getZ() {
            return Z;
        }

        public void setZ(double z) {
            Z = z;
        }
    }
}
