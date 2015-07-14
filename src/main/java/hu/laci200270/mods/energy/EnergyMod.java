package hu.laci200270.mods.energy;

import hu.laci200270.mods.energy.block.BlockUtil;
import hu.laci200270.mods.energy.fluid.FluidReference;
import hu.laci200270.mods.energy.proxy.CommonProxy;
import hu.laci200270.mods.energy.tile.TileDieselGenerator;
import hu.laci200270.mods.energy.tile.TileFluidNode;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = EnergyMod.modid)
public class EnergyMod {
	public static Logger logger;

	@SidedProxy(clientSide = "hu.laci200270.mods.energy.proxy.ClientProxy", serverSide = "hu.laci200270.mods.energy.proxy.CommonProxy")
	public static CommonProxy proxy;
	@Instance(value = EnergyMod.modid)
	public static EnergyMod modInstance;

	public static final String modid = "energymod";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("A lot of energy runs throught of Minecraft 1.8!");
		GameRegistry.registerBlock(BlockUtil.dieselGenerator,
				"dieselGenerator");
		GameRegistry.registerBlock(BlockUtil.fluidNode, "fluidNode");
		GameRegistry.registerBlock(BlockUtil.fluidPipe, "fluidPipe");
		proxy.preInit();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		GameRegistry
				.registerTileEntity(TileDieselGenerator.class, "tileDiesel");
		GameRegistry.registerTileEntity(TileFluidNode.class, "fluidNode");
		FluidRegistry.registerFluid(FluidReference.fluidDiesel);
		proxy.init();
	}
}
