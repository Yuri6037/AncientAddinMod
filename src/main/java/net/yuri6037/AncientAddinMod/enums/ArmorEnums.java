package net.yuri6037.AncientAddinMod.enums;

import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public enum ArmorEnums {

    ANCIENT_GLASSES("AncientGlasses", -1, new int[]{0, 0, 0, 0}, 0),
    HEAVY_ARMOR("HeavyArmor", 128, new int[]{4, 16, 8, 2}, 0);

    public ItemArmor.ArmorMaterial mat;
    public String id;
    ArmorEnums(String name, int dur, int[] damageReduc, int enchant){
        mat = EnumHelper.addArmorMaterial(name, dur, damageReduc, enchant);
        id = name;
    }
}
