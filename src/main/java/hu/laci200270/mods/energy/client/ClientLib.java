package hu.laci200270.mods.energy.client;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.b3d.B3DLoader;

import com.google.common.base.Function;

public class ClientLib {

	public static void renderB3DModel(TextureManager textureManager,
	                                  ResourceLocation modelLoc, int animationCounter) {
		IModel model = ModelLoaderRegistry.getModel(modelLoc);
		B3DLoader.B3DState defaultState = ((B3DLoader.Wrapper) model)
				.getDefaultState();
		B3DLoader.B3DState newState = new B3DLoader.B3DState(
				defaultState.getAnimation(), animationCounter);
		renderBlockModel(textureManager, model, newState);
	}

	public static void renderBlockModel(TextureManager textureManager,
	                                    IModel model, IModelState state) {
		if (state == null)
			state = model.getDefaultState();

		// Temporary fix for some models having alpha=0
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();

		final TextureMap textureMapBlocks = Minecraft.getMinecraft()
				.getTextureMapBlocks();

		VertexFormat vFormat = Attributes.DEFAULT_BAKED_FORMAT;
		IFlexibleBakedModel bakedModel = model.bake(state, vFormat,
				new Function<ResourceLocation, TextureAtlasSprite>() {
					@Nullable
					@Override
					public TextureAtlasSprite apply(
							@Nullable ResourceLocation input) {
						return input == null ? textureMapBlocks
								.getMissingSprite() : textureMapBlocks
								.getAtlasSprite(input.toString());
					}
				});
		textureManager.bindTexture(TextureMap.locationBlocksTexture);

		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.5f, 0.0f, -0.5f);

		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		worldRenderer.startDrawingQuads();
		worldRenderer.setVertexFormat(vFormat);
		worldRenderer.markDirty();

		for (BakedQuad quad : bakedModel.getGeneralQuads())
			worldRenderer.addVertexData(quad.getVertexData());
		for (EnumFacing facing : EnumFacing.values())
			for (BakedQuad quad : bakedModel.getFaceQuads(facing))
				worldRenderer.addVertexData(quad.getVertexData());

		tessellator.draw();

		GlStateManager.popMatrix();

		GlStateManager.enableAlpha(); // End temporary fix
	}

}
