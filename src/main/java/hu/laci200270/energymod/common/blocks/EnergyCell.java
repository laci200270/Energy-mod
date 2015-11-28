package hu.laci200270.energymod.common.blocks;

import hu.laci200270.energymod.EnergyMod;
import hu.laci200270.energymod.common.tile.TileEnergyCell;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by laci200270 on 2015.11.22. at 16:Energy-mod .
 */
public class EnergyCell extends Block {
    public EnergyCell() {
        super(Material.circuits);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEnergyCell();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ){
        try {
            EnergyMod.guiRegistry.openGui("eCell",playerIn,pos,worldIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
