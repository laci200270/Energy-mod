package hu.laci200270.mods.energy;

import hu.laci200270.mods.energy.block.BlockReference;
import hu.laci200270.mods.energy.fluid.FluidReference;
import hu.laci200270.mods.energy.proxy.CommonProxy;
import hu.laci200270.mods.energy.tile.TileDieselGenerator;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@Mod(modid = EnergyMod.modid)
public class EnergyMod {
	public static  Logger logger;
	
	@SidedProxy(clientSide="hu.laci200270.mods.energy.proxy.ClientProxy",serverSide="hu.laci200270.mods.energy.proxy.CommonProxy")
	public static CommonProxy proxy;
	@Instance
	public static EnergyMod modInstance;
	
	public static final String modid="energymod";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		logger=event.getModLog();
		logger.info("A lot of energy runs throught of Minecraft 1.8!");
		GameRegistry.registerBlock(BlockReference.dieselGenerator, "dieselGenerator");
		proxy.preInit();
	}
	
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		
		GameRegistry.registerTileEntity(TileDieselGenerator.class, "tileDiesel");
		FluidRegistry.registerFluid(FluidReference.fluidDiesel);
		proxy.init();
	}
}

