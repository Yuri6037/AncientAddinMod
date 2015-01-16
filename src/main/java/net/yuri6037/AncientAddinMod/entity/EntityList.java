package net.yuri6037.AncientAddinMod.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.yuri6037.AncientAddinMod.AncientAddin;

/**
 * Made by Yuri6037
 */
public class EntityList {

    private static int usingID = 128;

    private static void registerEntity(String id, Class<? extends Entity> entityClass){
        EntityRegistry.registerModEntity(entityClass, "ancientAddin." + id, usingID, AncientAddin.ancientAddin, 10, 1, true);
        usingID++;
    }

    static {
        registerEntity("ancientRayon", EntityAncientRayon.class);
        registerEntity("drone", EntityDrone.class);
    }
}
