package hu.laci200270.energymod.handler;

import hu.laci200270.energymod.models.ModelPipe;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author laci200270
 * @date 2015.08.18.
 */
public class BakeEventHandler {
    @SubscribeEvent
    public void bakeEvent(ModelBakeEvent event){
        event.modelRegistry.putObject(new ModelResourceLocation("energymod:eConduit","normal"),new ModelPipe(null));
    }

}
