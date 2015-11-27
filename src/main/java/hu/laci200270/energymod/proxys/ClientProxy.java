package hu.laci200270.energymod.proxys;

import hu.laci200270.energymod.EnergyMod;

/**
 * Created by laci200270 on 2015.11.27. at 22:Energy-mod .
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
        EnergyMod.energyCell.setCreativeTab(EnergyMod.creativeTabs);
        EnergyMod.conduitEnergy.setCreativeTab(EnergyMod.creativeTabs);
    }
}
