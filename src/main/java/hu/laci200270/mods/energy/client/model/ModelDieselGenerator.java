package hu.laci200270.mods.energy.client.model;

import hu.laci200270.mods.energy.tile.TileDieselGenerator;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDieselGenerator extends ModelBase
{
	
  //fields
    ModelRenderer doboz;
    ModelRenderer tengely;
    ModelRenderer body;
    ModelRenderer piston1;
    ModelRenderer piston2;
    ModelRenderer piston3;
    ModelRenderer piston4;
    ModelRenderer piston5;
    ModelRenderer piston6;
    ModelRenderer piston7;
    ModelRenderer piston8;
	private Entity generator;
  
  public ModelDieselGenerator(TileDieselGenerator generator)
  {
    this.generator=null;
	textureWidth = 64;
    textureHeight = 32;
    
      doboz = new ModelRenderer(this, 0, 0);
      doboz.addBox(0F, 0F, 0F, 9, 6, 7);
      doboz.setRotationPoint(0F, 0F, 0F);
      doboz.setTextureSize(64, 32);
      doboz.mirror = true;
      setRotation(doboz, 0F, 0F, 0F);
      tengely = new ModelRenderer(this, 38, 0);
      tengely.addBox(0F, 0F, 0F, 11, 2, 2);
      tengely.setRotationPoint(-1F, 2F, 2F);
      tengely.setTextureSize(64, 32);
      tengely.mirror = true;
      setRotation(tengely, 0.7853982F, 0F, 0F);
      body = new ModelRenderer(this, 1, 19);
      body.addBox(0F, 2F, 5F, 9, 1, 1);
      body.setRotationPoint(0F, 2F, -2F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0.7853982F, 0F, 0F);
      piston1 = new ModelRenderer(this, 33, 0);
      piston1.addBox(0F, 0F, 0F, 1, 3, 1);
      piston1.setRotationPoint(1F, 0F, 1F);
      piston1.setTextureSize(64, 32);
      piston1.mirror = true;
      setRotation(piston1, 0.7853982F, 0F, 0F);
      piston2 = new ModelRenderer(this, 33, 6);
      piston2.addBox(0F, 0F, 0F, 1, 3, 1);
      piston2.setRotationPoint(3F, 0F, 1F);
      piston2.setTextureSize(64, 32);
      piston2.mirror = true;
      setRotation(piston2, 0.7853982F, 0F, 0F);
      piston3 = new ModelRenderer(this, 1, 23);
      piston3.addBox(0F, 0F, 0F, 1, 3, 1);
      piston3.setRotationPoint(5F, 0F, 1F);
      piston3.setTextureSize(64, 32);
      piston3.mirror = true;
      setRotation(piston3, 0.7853982F, 0F, 0F);
      piston4 = new ModelRenderer(this, 40, 6);
      piston4.addBox(0F, 0F, 0F, 1, 3, 1);
      piston4.setRotationPoint(7F, 0F, 1F);
      piston4.setTextureSize(64, 32);
      piston4.mirror = true;
      setRotation(piston4, 0.7853982F, 0F, 0F);
      piston5 = new ModelRenderer(this, 50, 15);
      piston5.addBox(0F, 0F, 0F, 1, 3, 1);
      piston5.setRotationPoint(1F, -1F, 6F);
      piston5.setTextureSize(64, 32);
      piston5.mirror = true;
      setRotation(piston5, -0.7935489F, 0F, 0F);
      piston6 = new ModelRenderer(this, 55, 20);
      piston6.addBox(0F, 0F, 0F, 1, 3, 1);
      piston6.setRotationPoint(3F, -1F, 6F);
      piston6.setTextureSize(64, 32);
      piston6.mirror = true;
      setRotation(piston6, -0.7853982F, 0F, 0F);
      piston7 = new ModelRenderer(this, 60, 25);
      piston7.addBox(0F, 0F, 0F, 1, 3, 1);
      piston7.setRotationPoint(5F, -1F, 6F);
      piston7.setTextureSize(64, 32);
      piston7.mirror = true;
      setRotation(piston7, -0.7853982F, 0F, 0F);
      piston8 = new ModelRenderer(this, 55, 15);
      piston8.addBox(0F, 0F, 0F, 1, 3, 1);
      piston8.setRotationPoint(7F, -1F, 6F);
      piston8.setTextureSize(64, 32);
      piston8.mirror = true;
      setRotation(piston8, -0.7853982F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    doboz.render(f5);
    tengely.render(f5);
    body.render(f5);
    piston1.render(f5);
    piston2.render(f5);
    piston3.render(f5);
    piston4.render(f5);
    piston5.render(f5);
    piston6.render(f5);
    piston7.render(f5);
    piston8.render(f5);
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

