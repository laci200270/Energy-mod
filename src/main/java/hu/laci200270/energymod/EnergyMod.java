package hu.laci200270.energymod;

import hu.laci200270.energymod.client.CreativeTabEnergyMod;
<<<<<<< HEAD
import hu.laci200270.energymod.client.gui.GuiEnergyCell;
import hu.laci200270.energymod.common.blocks.EnergyCell;
=======
>>>>>>> parent of 6c87ec8... Started to work on GUI registry and energy cell GUI
import hu.laci200270.energymod.common.blocks.EnergyConduit;
import hu.laci200270.energymod.common.tile.TileEnergyCell;
import hu.laci200270.energymod.common.tile.TileEnergyConduit;
import hu.laci200270.energymod.proxys.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

/**
 * @author laci200270
 *
 */
@Mod(modid = "energymod")
public class EnergyMod {
    public static final EnergyConduit conduitEnergy=new EnergyConduit();
    public static final EnergyCell energyCell =new EnergyCell();
    public static final CreativeTabs creativeTabs = new CreativeTabEnergyMod();
    @Mod.Instance(value = "energymod")
    public static EnergyMod instance;


    public static Logger logger=null;

    @SidedProxy(serverSide = "hu.laci200270.energymod.proxys.CommonProxy", clientSide = "hu.laci200270.energymod.proxys.ClientProxy")
    public static CommonProxy proxy;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger=event.getModLog();
        logger.info("Lot of energy runs through of Minecraft 1.8!");
        if(Loader.isModLoaded("CoFHCore")){
            logger.info("What happened?CofhCore updated to 1.8?Its impossible.");
        }

        GameRegistry.registerBlock(conduitEnergy, "eConduit");
        GameRegistry.registerTileEntity(TileEnergyConduit.class, "teConduit");
        GameRegistry.registerBlock(energyCell, "eCell");
        GameRegistry.registerTileEntity(TileEnergyCell.class, "teCell");

        proxy.preInit();

    }

}
