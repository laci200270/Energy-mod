package hu.laci200270.energymod.proxys;

import hu.laci200270.energymod.EnergyMod;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by laci200270 on 2015.11.27. at 22:Energy-mod .
 */
public class CommonProxy {

    public void preInit() {
        NetworkRegistry.INSTANCE.registerGuiHandler(EnergyMod.instance,EnergyMod.guiHandler);
    }


}
