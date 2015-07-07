package hu.laci200270.mods.energy.client.model;

import hu.laci200270.mods.energy.tile.TileDieselGenerator;

import java.io.IOException;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.client.model.b3d.B3DLoader;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Function;

public class ModelDieselGenerator extends ModelBase
{
	
	private static final ModelResourceLocation engineModelFile = new ModelResourceLocation("energymod:models/block/engine.b3d");
	Function < ResourceLocation, TextureAtlasSprite > textureGetter = new Function < ResourceLocation, TextureAtlasSprite > () {
		public TextureAtlasSprite apply(ResourceLocation location) {
			return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
		}
	};
  public ModelDieselGenerator(TileDieselGenerator generator)
  {
 
    

  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
   
    Tessellator tessellator = Tessellator.getInstance();
	WorldRenderer worldrenderer = tessellator.getWorldRenderer();
	IModel engineModel=null;
    engineModel = B3DLoader.instance.loadModel(engineModelFile);
	@SuppressWarnings("deprecation")
	IBakedModel bakedBullet = engineModel.bake((TRSRTransformation.identity()), Attributes.DEFAULT_BAKED_FORMAT, textureGetter);
	worldrenderer.startDrawingQuads();
	//Get Quads
	List < BakedQuad > generalQuads = bakedBullet.getGeneralQuads();
	for (BakedQuad q: generalQuads) {
		int[] vd = q.getVertexData();
		worldrenderer.setVertexFormat(Attributes.DEFAULT_BAKED_FORMAT);
		worldrenderer.addVertexData(vd);
	}
	for (EnumFacing face: EnumFacing.values()) {
		List < BakedQuad > faceQuads = bakedBullet.getFaceQuads(face);
		for (BakedQuad q: faceQuads) {
			int[] vd = q.getVertexData();
			worldrenderer.setVertexFormat(Attributes.DEFAULT_BAKED_FORMAT);
			worldrenderer.addVertexData(vd);
		}
	}
	tessellator.draw();
	GL11.glPopMatrix();

  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5,null );
  }

}

