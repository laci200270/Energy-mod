package hu.laci200270.energymod;

import hu.laci200270.energymod.blocks.EnergyConduit;
import hu.laci200270.energymod.handler.BakeEventHandler;
import hu.laci200270.energymod.handler.ModelLoaderEnergyMod;
import hu.laci200270.energymod.tile.TileEnergyConduit;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

/**
 * @author laci200270
 *
 */
@Mod(modid = "energymod")
public class EnergyMod {
    public static final EnergyConduit conduitEnergy=new EnergyConduit();
    @Mod.Instance(value = "energymod")
    public static EnergyMod instance;


    public static Logger logger=null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger=event.getModLog();
        logger.info("Lot of energy runs through of Minecraft 1.8!");
        if(Loader.isModLoaded("CoFHCore")){
            logger.info("What happened?CofhCore updated to 1.8?Its impossible.");
        }
        GameRegistry.registerBlock(conduitEnergy, "eConduit");
        GameRegistry.registerTileEntity(TileEnergyConduit.class, "teConduit");
        MinecraftForge.EVENT_BUS.register(new BakeEventHandler());
        //MinecraftForge.
        if (event.getSide() == Side.CLIENT) {
            ModelLoaderRegistry.registerLoader(new ModelLoaderEnergyMod());

        }

    }

}
