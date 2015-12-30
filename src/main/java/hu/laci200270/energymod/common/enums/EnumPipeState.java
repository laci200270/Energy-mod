package hu.laci200270.energymod.common.enums;

/**
 * @author laci200270
 * @date 2015.08.18.
 */

import net.minecraft.util.IStringSerializable;

/**
 * CONNECTED when it connected to a machine or inventory
 * <p/>
 * TRANSFER when its next to a pipe
 * <p/>
 * NONE if nothing
 */
public enum EnumPipeState implements IStringSerializable {


    CONNECTED {
        @Override
        public String getName() {
            return "connected";
        }
    }, TRANSFER {
        @Override
        public String getName() {
            return "transfer";
        }
    }, NONE {
        @Override
        public String getName() {
            return "none";
        }
    };

    public static final EnumPipeState[] VALUES = {NONE, CONNECTED, TRANSFER};

}
