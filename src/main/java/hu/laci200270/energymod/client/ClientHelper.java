package hu.laci200270.energymod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.TRSRTransformation;
import org.lwjgl.opengl.GL11;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

/**
 * Created by Laci on 2015. 12. 28..
 */
public class ClientHelper {
    public void setRotation(TileEntity tileEntity){
        Vector3d teLoc = new Vector3d(tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ());
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        Vector3d playerLoc = new Vector3d();
        playerLoc.setX(player.posX);
        playerLoc.setY(player.posY + player.getEyeHeight());
        playerLoc.setZ(player.posZ);
        Vector3d lookVec = new Vector3d(playerLoc.getX() - teLoc.getX(), playerLoc.getY() - teLoc.getY(), playerLoc.getZ() - teLoc.getZ());
        double angleYaw = Math.atan2(lookVec.getZ(), lookVec.getX()) - Math.PI/2d;
        double anglePitch = Math.atan2(lookVec.getY(), Math.sqrt(lookVec.getX() * lookVec.getX() + lookVec.getZ() * lookVec.getZ()));


        GL11.glRotated(angleYaw, 1, 0, 0);
    }
}
