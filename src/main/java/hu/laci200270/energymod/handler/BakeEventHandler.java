package hu.laci200270.energymod.handler;

import hu.laci200270.energymod.EnergyMod;
import hu.laci200270.energymod.tile.ModelPipe;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.ArrayList;



/**
 * @author laci200270
 * @date 2015.08.18.
 */
public class BakeEventHandler {
    private static Field getField(Class clazz, String fieldName)
            throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void bakeEvent(ModelBakeEvent event){
        //Object obj = event.modelRegistry.getObject(new ModelResourceLocation("energymod:eConduit"));
        EnergyMod.logger.info("Bake event");
        event.modelRegistry.putObject(new ModelResourceLocation("energymod:eConduit#inventory"), new ModelPipe(null));
        ArrayList<String> all = new ArrayList<String>();
        /** for (int i = 0; i < 729; i++) {
            int east = 0;
            int west = 0;
            int south = 0;
            int north = 0;
            int down = 0;
            int up = 0;

            int number = i;

            east = number % 3;
            number = number / 3;
            down = number % 3;
            number = number / 3;
            up = number % 3;
            number = number / 3;
            west = number % 3;
            number = number / 3;
            south = number % 3;
            number = number / 3;
            north = number % 3;

            EnumPipeState Up = EnumPipeState.VALUES[up];
            EnumPipeState Down = EnumPipeState.VALUES[down];
            EnumPipeState South = EnumPipeState.VALUES[south];
            EnumPipeState North = EnumPipeState.VALUES[north];
            EnumPipeState West = EnumPipeState.VALUES[west];
            EnumPipeState East = EnumPipeState.VALUES[east];

            HashMap<EnumFacing, EnumPipeState> map = new HashMap<EnumFacing, EnumPipeState>();
            map.put(EnumFacing.EAST, East);
            map.put(EnumFacing.DOWN, Down);
            map.put(EnumFacing.UP, Up);
            map.put(EnumFacing.SOUTH, South);
            map.put(EnumFacing.WEST, West);
            map.put(EnumFacing.NORTH, North);
            String key = "energymod:eConduit#down=" + Down.getName() + ",east=" + East.getName() + ",north=" + North.getName() + ",south=" + South.getName() + ",up=" + Up.getName() + ",west=" + West.getName();
            if (all.contains(key)) {
                //throw new RuntimeException("ALreadyCOntains");
                EnergyMod.logger.warn("Key present twice:" + key);
            }
            all.add(key);
            //EnergyMod.logger.error(key);
            event.modelRegistry.putObject(new ModelResourceLocation(key), new ModelPipe(map));


         }*/


    }
}


