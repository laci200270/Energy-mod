package hu.laci200270.mods.energy.dummyclasses;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class DummyBlockStore {
	public BlockPos position;
	public Block block;
	
	
	public DummyBlockStore(BlockPos position, Block block) {
		
		this.position = position;
		this.block = block;
	}
	
	
	
}
