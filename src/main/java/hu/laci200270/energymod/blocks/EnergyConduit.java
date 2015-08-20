package hu.laci200270.energymod.blocks;

import cofh.api.energy.IEnergyReceiver;
import hu.laci200270.energymod.EnergyMod;
import hu.laci200270.energymod.tile.TileEnergyConduit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author laci200270
 * @date 2015.08.16.
 */
public class EnergyConduit extends Block {


    public static final PropertyInteger EAST = PropertyInteger.create("east",0,2);
    public static final PropertyInteger SOUTH = PropertyInteger.create("south",0,2);
    public static final PropertyInteger WEST = PropertyInteger.create("west",0,2);
    public static final PropertyInteger NORTH = PropertyInteger.create("north", 0, 2);
    public static final PropertyInteger UP = PropertyInteger.create("up",0,2);
    public static final PropertyInteger DOWN = PropertyInteger.create("down",0,2);


    public EnergyConduit(){
        super(Material.iron);


    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        state=state.withProperty(EAST,generateState(EnumFacing.EAST,world,pos));
        state=state.withProperty(WEST,generateState(EnumFacing.WEST,world,pos));
        state = state.withProperty(NORTH, generateState(EnumFacing.NORTH, world, pos));
        state=state.withProperty(SOUTH,generateState(EnumFacing.SOUTH,world,pos));
        state=state.withProperty(UP,generateState(EnumFacing.UP,world,pos));
        state=state.withProperty(DOWN,generateState(EnumFacing.DOWN,world,pos));
        return  state;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEnergyConduit();

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    private int generateState(EnumFacing facing,IBlockAccess world,BlockPos pos){
        if(world.getBlockState(pos.offset(facing)).getBlock()== EnergyMod.conduitEnergy){
            return 1;
        }
        if(world.getTileEntity(pos.offset(facing))!=null&&world.getTileEntity(pos.offset(facing)) instanceof IEnergyReceiver){
            return 2;
        }
        else
            return 0;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, EAST, SOUTH, WEST, UP, DOWN, NORTH);
    }
}
