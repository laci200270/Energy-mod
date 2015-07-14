package hu.laci200270.mods.energy.block;

import net.minecraft.block.Block;

public class BlockUtil {

	public static BlockDieselGenerator dieselGenerator = new BlockDieselGenerator();
	public static BlockFluidPipe fluidPipe=new BlockFluidPipe();
	public static BlockFluidNode fluidNode=new BlockFluidNode();

	public static boolean isPipe(Block block) {
		// TODO: Replace with proper registry service
		if (   block == fluidPipe
		    || block == fluidNode)
			return true;
		else
			return false;
	}
}
