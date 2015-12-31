package hu.laci200270.energymod.common.blocks;

import hu.laci200270.energymod.common.tile.TileEnergyCell;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by laci200270 on 2015.11.22. at 16:Energy-mod .
 */
public class EnergyCell extends Block {
    public EnergyCell() {
        super(Material.circuits);
        setUnlocalizedName("eCell");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
       if(!worldIn.isRemote) {
           TileEnergyCell eCell = (TileEnergyCell) worldIn.getTileEntity(pos);
           int received = eCell.receiveEnergy(side, 2000, false);
           playerIn.addChatComponentMessage(new ChatComponentText(String.format("Player %s right clicked to block and added %d energy to cell at %s, in side %s", playerIn.getName(), received, pos.toString(), side)));
           if (playerIn.isSneaking())
               playerIn.addChatComponentMessage(new ChatComponentText(String.format("The current energy level is %d", eCell.getEnergyStored(EnumFacing.UP))));
       }
        return true;
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
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }
}
