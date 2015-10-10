package hu.laci200270.energymod.handler;

import hu.laci200270.energymod.EnergyMod;
import hu.laci200270.energymod.tile.ModelPipe;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author laci200270
 * @date 2015.08.18.
 */
public class BakeEventHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void bakeEvent(ModelBakeEvent event){
        //Object obj = event.modelRegistry.getObject(new ModelResourceLocation("energymod:eConduit"));
        EnergyMod.logger.info("Bake event");
        event.modelRegistry.putObject(new ModelResourceLocation("energymod:eConduit#inventory"), new ModelPipe(null));
        // Minecraft.getMinecraft().getRenderItem().getItemModelMesher().

    }

}
