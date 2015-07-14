package hu.laci200270.mods.energy.proxy;

import hu.laci200270.mods.energy.EnergyMod;
import hu.laci200270.mods.energy.block.BlockUtil;
import hu.laci200270.mods.energy.client.ClientReference;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		EnergyMod.logger.debug("ClientPorxy Init!");
		BlockUtil.dieselGenerator.setCreativeTab(ClientReference.energy);
		// ClientRegistry.bindTileEntitySpecialRenderer(TileDieselGenerator.class,
		// new DieselGeneratorSpecialRenderer());

	}

	@Override
	public void preInit() {

		B3DLoader.instance.addDomain(EnergyMod.modid.toLowerCase());
		Item item = Item.getItemFromBlock(BlockUtil.dieselGenerator);
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(EnergyMod.modid.toLowerCase()
						+ ":engine.b3d", "inventory"));
		// B3DLoader.instance.accepts(modelLocation)

	}

}
