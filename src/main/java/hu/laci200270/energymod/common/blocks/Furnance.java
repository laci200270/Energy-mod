package hu.laci200270.energymod.common.blocks;

import hu.laci200270.energymod.common.tile.TileFurnance;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Laci on 2016. 01. 01..
 */
public class Furnance extends Block {
    public Furnance() {
        super(Material.fire);
        setUnlocalizedName("energizedFurnance");
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileFurnance();
    }
}
