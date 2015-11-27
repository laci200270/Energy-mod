package hu.laci200270.energymod.common.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created by laci200270 on 2015.11.22. at 16:Energy-mod .
 */
public enum EnumSideMode implements IStringSerializable {

    INPUT {
        @Override
        public String getName() {
            return "input";
        }
    },
    OUTPUT {
        @Override
        public String getName() {
            return "output";
        }
    },
    NONE {
        @Override
        public String getName() {
            return "none";
        }
    }

}
