package hu.laci200270.energymod;


import hu.laci200270.energymod.common.blocks.EnergyCell;
import hu.laci200270.energymod.common.blocks.EnergyConduit;
import hu.laci200270.energymod.common.blocks.StrilingGenerator;
import hu.laci200270.energymod.common.tile.TileEnergyCell;
import hu.laci200270.energymod.common.tile.TileEnergyConduit;
import hu.laci200270.energymod.common.tile.TileGenerator;
import hu.laci200270.energymod.proxys.CommonProxy;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

/**
 * @author laci200270
 */
@Mod(modid = "energymod")
public class EnergyMod {
    public static final EnergyConduit conduitEnergy = new EnergyConduit();
    public static final EnergyCell energyCell = new EnergyCell();
    public static final StrilingGenerator generator = new StrilingGenerator();


    @Mod.Instance(value = "energymod")
    public static EnergyMod instance;


    public static Logger logger = null;

    @SidedProxy(serverSide = "hu.laci200270.energymod.proxys.CommonProxy", clientSide = "hu.laci200270.energymod.proxys.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Lot of energy runs through of Minecraft 1.8!");
        if (Loader.isModLoaded("CoFHCore")) {
            logger.info("What happened?CofhCore updated to 1.8?Its impossible.");
        }

        GameRegistry.registerBlock(conduitEnergy, "eConduit");
        GameRegistry.registerTileEntity(TileEnergyConduit.class, "teConduit");
        GameRegistry.registerBlock(energyCell, "eCell");
        GameRegistry.registerTileEntity(TileEnergyCell.class, "teCell");
        GameRegistry.registerBlock(generator, "generator");
        GameRegistry.registerTileEntity(TileGenerator.class,"teGenerator");

        proxy.preInit(event);

    }

}
