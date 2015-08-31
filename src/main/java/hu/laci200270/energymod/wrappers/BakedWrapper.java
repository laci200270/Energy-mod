package hu.laci200270.energymod.wrappers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IFlexibleBakedModel;

import java.util.List;

/**
 * @author laci200270
 * @date 2015.08.31.
 */

@SuppressWarnings("deprecation")
public class BakedWrapper implements IFlexibleBakedModel {

    private final IBakedModel source;

    public BakedWrapper(IBakedModel source) {
        this.source = source;
    }

    public BakedWrapper(String resLoc) {
        this(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(new ModelResourceLocation("minecraft:oaklog")));
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side) {
        return source.getFaceQuads(side);
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return source.getGeneralQuads();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return source.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return source.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return source.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getTexture() {
        return source.getTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return source.getItemCameraTransforms();
    }

    @Override
    public VertexFormat getFormat() {
        return new VertexFormat();
    }
}
