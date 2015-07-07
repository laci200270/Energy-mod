package hu.laci200270.mods.energy.client.render;

import hu.laci200270.mods.energy.client.ClientLib;
import hu.laci200270.mods.energy.client.model.ModelDieselGenerator;
import hu.laci200270.mods.energy.tile.TileDieselGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class DieselGeneratorSpecialRenderer extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x,
			double y, double z, float f,
			int integer) {
		//	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("energymod:models/textures/texture"));
			GL11.glPushMatrix();
			GL11.glTranslated(x+1F, y+0.75F, z);
			GL11.glScalef(1F, 1F, 1F);
			GL11.glPushMatrix();
			GL11.glRotatef(180F, 0F,0F, 1.0F);
			GL11.glPushMatrix();
			//ClientLib.renderB3DModel(Minecraft.getMinecraft().getTextureManager(), new ResourceLocation("energymod","engine"), 20);
		//	net.minecraftforge.client.model.b3d.B3DLoader.instance.loadModel(new ResourceLocation("energymod:assets/models/enginbe.b3d")).bake(null, null, null).
			new ModelDieselGenerator((TileDieselGenerator) tileEntity).render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		
		
	}

}
