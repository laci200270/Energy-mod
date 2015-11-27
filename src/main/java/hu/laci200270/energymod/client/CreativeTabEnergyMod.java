package hu.laci200270.energymod.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by laci200270 on 2015.11.27. at 22:Energy-mod .
 */
@SideOnly(Side.CLIENT)
public class CreativeTabEnergyMod extends CreativeTabs {

    public CreativeTabEnergyMod() {
        super("Energy Mod");
    }

    @Override
    public Item getTabIconItem() {
        return Items.repeater;
    }
}
