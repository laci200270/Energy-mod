package hu.laci200270.energymod.common.gui;

import hu.laci200270.energymod.EnergyMod;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.lang.model.element.UnknownElementException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by laci200270 on 2015.11.28. at 19:Energy-mod .
 */
public class GuiRegistry {

    private LinkedHashMap<String,Gui_ContainerClassPair> datas=new LinkedHashMap<String, Gui_ContainerClassPair>();

    public void registerElement(String id,Class<? extends Container> container,Class<? extends Gui> gui){

        datas.put(id, new Gui_ContainerClassPair(gui, container));

    }

    public void openGui(String key,EntityPlayer player,BlockPos pos,World world) throws Exception {
        int id=getPosOfElement(key,datas);
        if(id==-1)
            throw new Exception(String.format("The obejct with key '%s' not found",id));
        player.openGui(EnergyMod.instance, id, world, pos.getX(), pos.getY(), pos.getZ());
    }



    private int getPosOfElement(Object key,LinkedHashMap map){
        for(int i=0;i<map.keySet().size();i++){
            if(map.keySet().toArray()[i].equals(key))
                return i;
        }
        return -1;
    }

    @Nullable
    public GuiContainerPair getElement(String id,TileEntity tileEntity,EntityPlayer player){
        Gui_ContainerClassPair gconpair=datas.get(id);
        try {
            Container container= (Container) gconpair.container.getConstructor(new Class[]{TileEntity.class, EntityPlayer.class}).newInstance(tileEntity,player);
            Gui gui= (Gui) gconpair.gui.getConstructor(new Class[]{Container.class}).newInstance(container);
            GuiContainerPair pair=new GuiContainerPair(container,gui);
            return pair;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }



    }

    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return getElement((String) datas.keySet().toArray()[id],world.getTileEntity(new BlockPos(x,y,z)),player).container;
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return getElement((String) datas.keySet().toArray()[id],world.getTileEntity(new BlockPos(x,y,z)),player).gui;
    }

    private class Gui_ContainerClassPair {
        private Class<? extends Gui> gui;
        private Class<? extends Container> container;

        public Gui_ContainerClassPair(Class<? extends Gui> gui, Class<? extends Container> container) {
            this.gui = gui;
            this.container = container;
        }



    }

    public class GuiContainerPair {

        private final Container container;
        private final Gui gui;

        protected GuiContainerPair(Container container,Gui gui){
            this.container=container;
            this.gui=gui;
        }

    }

}
