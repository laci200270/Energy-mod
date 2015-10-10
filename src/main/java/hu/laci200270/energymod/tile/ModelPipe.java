package hu.laci200270.energymod.tile;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import hu.laci200270.energymod.enums.EnumPipeState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Laci on 2015.10.09..
 */
@SuppressWarnings("ALL")
public class ModelPipe implements IBakedModel {


    public static MultiModel model = null;
    protected static HashMap<EnumFacing, EnumPipeState> inventoryHashMap = new HashMap<EnumFacing, EnumPipeState>();

    static {
        for (EnumFacing current: EnumFacing.VALUES){
            inventoryHashMap.put(current,EnumPipeState.NONE);
        }
    }

    private IBakedModel baked = null;

    public ModelPipe(HashMap<EnumFacing,EnumPipeState> world){
        IModel oakLog = null;
        try {
            oakLog = ModelLoaderRegistry.getModel(new ModelResourceLocation("minecraft:block/oak_log"));
            ImmutableMap.Builder<String, Pair<IModel, IModelState>> models = ImmutableMap.builder();
           if(world==null){
               world=inventoryHashMap;
           }
            for(EnumFacing current:EnumFacing.VALUES){
                EnumPipeState state=world.get(current);
                if(state==EnumPipeState.CONNECTED){

                }
            }
            model = new MultiModel(oakLog, new TRSRTransformation(ItemTransformVec3f.DEFAULT), models.build());
            Function<ResourceLocation, TextureAtlasSprite> textureGetter = new Function<ResourceLocation, TextureAtlasSprite>() {
                public TextureAtlasSprite apply(ResourceLocation location) {
                    return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
                }
            };
            baked = model.bake(ItemTransformVec3f.DEFAULT, Attributes.DEFAULT_BAKED_FORMAT, textureGetter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public IBakedModel handleItemState(ItemStack stack) {
        return new ModelPipe(inventoryHashMap);
    }


    @Override
    public List getFaceQuads(EnumFacing p_177551_1_) {
        return baked.getFaceQuads(p_177551_1_);
    }

    @Override
    public List getGeneralQuads() {
        return baked.getGeneralQuads();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return baked.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return baked.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return baked.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getTexture() {
        return baked.getTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return baked.getItemCameraTransforms();
    }
}
