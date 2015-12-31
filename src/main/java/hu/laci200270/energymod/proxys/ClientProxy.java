package hu.laci200270.energymod.proxys;

import hu.laci200270.energymod.EnergyMod;
import hu.laci200270.energymod.client.CreativeTabEnergyMod;
import hu.laci200270.energymod.client.tesrs.TesrEnergyCell;
import hu.laci200270.energymod.client.tesrs.TesrGenerator;
import hu.laci200270.energymod.common.tile.TileEnergyCell;
import hu.laci200270.energymod.common.tile.TileGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by laci200270 on 2015.11.27. at 22:Energy-mod .
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public static final CreativeTabs creativeTabs = new CreativeTabEnergyMod();

    @Override
    public void preInit(FMLPreInitializationEvent preInitializationEvent) {
        super.preInit(preInitializationEvent);
        EnergyMod.energyCell.setCreativeTab(creativeTabs);
        EnergyMod.conduitEnergy.setCreativeTab(creativeTabs);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyCell.class, new TesrEnergyCell());
        ClientRegistry.bindTileEntitySpecialRenderer(TileGenerator.class,new TesrGenerator());


    }
}
