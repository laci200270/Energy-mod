package hu.laci200270.energymod.blocks;

import cofh.api.energy.IEnergyReceiver;
import hu.laci200270.energymod.EnergyMod;
import hu.laci200270.energymod.enums.EnumPipeState;
import hu.laci200270.energymod.tile.TileEnergyConduit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * @author laci200270
 * @date 2015.08.16.
 */
public class EnergyConduit extends Block {

    public static PropertyEnum EAST = PropertyEnum.create("east", EnumPipeState.class);

    public static PropertyEnum SOUTH = PropertyEnum.create("south", EnumPipeState.class);
    public static PropertyEnum WEST = PropertyEnum.create("west", EnumPipeState.class);
    public static PropertyEnum NORTH = PropertyEnum.create("north", EnumPipeState.class);
    public static PropertyEnum UP = PropertyEnum.create("up", EnumPipeState.class);
    public static PropertyEnum DOWN = PropertyEnum.create("down", EnumPipeState.class);


    public EnergyConduit(){
        super(Material.iron);


    }


    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEnergyConduit();


    }

    public ArrayList<IBlockState> getAllStates() {

        ArrayList<IBlockState> states = new ArrayList<IBlockState>();
        for (int i = 0; i < getBlockState().getValidStates().size(); i++) {
            states.add((IBlockState) getBlockState().getValidStates().get(i));
        }
        return states;

    }


    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        state = state.withProperty(EAST, generateState(EnumFacing.EAST, world, pos));
        state = state.withProperty(WEST, generateState(EnumFacing.WEST, world, pos));
        state = state.withProperty(NORTH, generateState(EnumFacing.NORTH, world, pos));
        state = state.withProperty(SOUTH, generateState(EnumFacing.SOUTH, world, pos));
        state = state.withProperty(UP, generateState(EnumFacing.UP, world, pos));
        state = state.withProperty(DOWN, generateState(EnumFacing.DOWN, world, pos));
        return state;
    }


    @Override
    public BlockState getBlockState() {
        return super.getBlockState();

    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, EAST, WEST, DOWN, NORTH, SOUTH, UP);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    private EnumPipeState generateState(EnumFacing facing, IBlockAccess world, BlockPos pos) {
        if(world.getBlockState(pos.offset(facing)).getBlock()== EnergyMod.conduitEnergy){
            return EnumPipeState.TRANSFER;
        }
        if(world.getTileEntity(pos.offset(facing))!=null&&world.getTileEntity(pos.offset(facing)) instanceof IEnergyReceiver){
            return EnumPipeState.CONNECTED;
        }
        else
            return EnumPipeState.NONE;
    }


    @Override
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
