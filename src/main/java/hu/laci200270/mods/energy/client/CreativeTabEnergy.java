package hu.laci200270.mods.energy.client;

import hu.laci200270.mods.energy.block.BlockReference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class CreativeTabEnergy extends CreativeTabs {

	public CreativeTabEnergy(String label) {
		super(label);

	}

	@Override
	public Item getTabIconItem() {

		return new ItemBlock(BlockReference.dieselGenerator);
	}

}
