package hu.laci200270.mods.energy.proxy;

import hu.laci200270.mods.energy.EnergyMod;
import hu.laci200270.mods.energy.block.BlockReference;
import hu.laci200270.mods.energy.client.ClientReference;
import hu.laci200270.mods.energy.client.render.DieselGeneratorSpecialRenderer;
import hu.laci200270.mods.energy.tile.TileDieselGenerator;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		EnergyMod.logger.debug("ClientPorxy Init!");
		BlockReference.dieselGenerator.setCreativeTab(ClientReference.energy);
		ClientRegistry.bindTileEntitySpecialRenderer(TileDieselGenerator.class, new DieselGeneratorSpecialRenderer());
		
	}
	
	@Override
	public void preInit() {
		
		B3DLoader.instance.addDomain(EnergyMod.modid.toLowerCase());
		 	Item item = Item.getItemFromBlock(BlockReference.dieselGenerator);
	        ModelBakery.addVariantName(item, EnergyMod.modid + ":engine.b3d");

	        
	}
	
	

	
}
