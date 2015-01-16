package net.yuri6037.AncientAddinMod.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.*;

public class AnciantDamageSource extends DamageSource {

    private Entity ancientWeapon;

    public AnciantDamageSource(Entity weapon) {
        super("ancientDamage");
        ancientWeapon = weapon;
    }

    public IChatComponent func_151519_b(EntityLivingBase dead) {
        String s = "death.attack." + this.damageType;
        String plyName = dead.getCommandSenderName();
        String weapName = ancientWeapon.getCommandSenderName();
        weapName = StatCollector.translateToLocal(weapName);
        s = StatCollector.translateToLocal(s);
        s = s.replace("%s", plyName);
        s = s.replace("%g", weapName);

        ChatComponentText text = new ChatComponentText(s);
        return text;
    }

    public static void causeAncientDamage(EntityLivingBase attacked, Entity weapon, float f){
        attacked.attackEntityFrom(new AnciantDamageSource(weapon), f);
    }
}
