package hu.laci200270.energymod.tile;

import com.google.common.collect.ImmutableMap;
import hu.laci200270.energymod.blocks.EnergyConduit;
import hu.laci200270.energymod.enums.EnumPipeState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.*;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Laci on 2015.10.09..
 */
@SuppressWarnings(value = "deprecated,unused")
public class ModelPipe implements ISmartBlockModel, ISmartItemModel {


    protected static HashMap<EnumFacing,EnumPipeState> inventoryHashMap=new HashMap<EnumFacing,EnumPipeState>();
    public static MultiModel model = null;
    private IBakedModel baked = null;
    static {
        for (EnumFacing current: EnumFacing.VALUES){
            inventoryHashMap.put(current,EnumPipeState.NONE);
        }
    }

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
            model = new MultiModel(oakLog, null, models.build());
            baked = model.bake(ItemTransformVec3f.DEFAULT, new VertexFormat(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private EnumPipeState parseInteger(int i){
        if(i==0)
            return EnumPipeState.NONE;
        if(i==1)
            return EnumPipeState.TRANSFER;
        if (i==2)
            return EnumPipeState.CONNECTED;
        return null;

    }
    @Override
    public IBakedModel handleBlockState(IBlockState iBlockState) {
        HashMap<EnumFacing,EnumPipeState> worldHashMap=new HashMap<EnumFacing, EnumPipeState>();
        worldHashMap.put(EnumFacing.DOWN,parseInteger((Integer)iBlockState.getValue(EnergyConduit.DOWN)));
        worldHashMap.put(EnumFacing.UP,parseInteger((Integer)iBlockState.getValue(EnergyConduit.UP)));
        worldHashMap.put(EnumFacing.EAST,parseInteger((Integer)iBlockState.getValue(EnergyConduit.EAST)));
        worldHashMap.put(EnumFacing.WEST,parseInteger((Integer)iBlockState.getValue(EnergyConduit.WEST)));
        worldHashMap.put(EnumFacing.SOUTH, parseInteger((Integer) iBlockState.getValue(EnergyConduit.SOUTH)));

        worldHashMap.put(EnumFacing.NORTH, parseInteger((Integer) iBlockState.getValue(EnergyConduit.NORTH)));
        return new ModelPipe(worldHashMap);

    }

    @Override
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
