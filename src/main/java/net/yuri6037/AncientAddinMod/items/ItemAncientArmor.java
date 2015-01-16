package net.yuri6037.AncientAddinMod.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.yuri6037.AncientAddinMod.enums.ArmorEnums;

public class ItemAncientArmor extends ItemArmor {

    private ArmorEnums armorMaterial;

    public ItemAncientArmor(ArmorEnums material, int type){
        super(material.mat, 0, type);
        armorMaterial = material;

        if (convertArmorElementToString(type) != null) {
            setTextureName("AncientAddinMod:" + material.id + "_" + convertArmorElementToString(type));
        }
    }

    private String convertArmorElementToString(int id){
        switch(id){
            case 0: return "helmet";
            case 1: return "body";
            case 2: return "legs";
            case 3: return "boots";
            default: return null;
        }
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (armorType == 0 || armorType == 1 || armorType == 3){
            return "AncientAddinMod:textures/armors/" + armorMaterial.id + "_0.png";
        } else {
            return "AncientAddinMod:textures/armors/" + armorMaterial.id + "_1.png";
        }
    }
}
