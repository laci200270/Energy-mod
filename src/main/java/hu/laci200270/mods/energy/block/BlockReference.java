package hu.laci200270.mods.energy.block;

public class BlockReference {

	public static BlockDieselGenerator dieselGenerator =new BlockDieselGenerator();
	public static BlockDieselGenerator getDieselGenerator(){
		if (dieselGenerator==null){
			 dieselGenerator =new BlockDieselGenerator();
		}
		return dieselGenerator;
	}
}
