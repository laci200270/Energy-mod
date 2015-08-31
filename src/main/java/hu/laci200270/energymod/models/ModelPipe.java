package hu.laci200270.energymod.models;

import hu.laci200270.energymod.blocks.EnergyConduit;
import hu.laci200270.energymod.enums.EnumPipeState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.client.model.ISmartItemModel;
import net.minecraftforge.client.model.MultiModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author laci200270
 * @date 2015.08.18.
 */
public class ModelPipe implements IBakedModel,ISmartBlockModel,ISmartItemModel{


    protected static HashMap<EnumFacing,EnumPipeState> inventoryHashMap=new HashMap<EnumFacing,EnumPipeState>();

    static {
            for (EnumFacing current: EnumFacing.VALUES){
                inventoryHashMap.put(current,EnumPipeState.NONE);
            }
    }

    public ModelPipe(HashMap<EnumFacing,EnumPipeState> values){
        //IModel oakLog= Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes();
        MultiModel model = new MultiModel.Baked()
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
    public IBakedModel handleItemState(ItemStack itemStack) {
        return new ModelPipe(inventoryHashMap);
    }

    @Override
    public List getFaceQuads(EnumFacing enumFacing) {
        ArrayList<BakedQuad> quads = new ArrayList<BakedQuad>();


        return Collections.emptyList();
    }

    @Override
    public List getGeneralQuads() {
        return null;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getTexture() {
        return null;
    }


    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return null;
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
}
