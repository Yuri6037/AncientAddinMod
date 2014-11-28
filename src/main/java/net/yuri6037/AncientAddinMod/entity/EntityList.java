package net.yuri6037.AncientAddinMod.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.yuri6037.AncientAddinMod.AncientAddin;

/**
 * Made by Yuri6037
 */
public class EntityList {

    private static void registerEntity(String id, Class<? extends Entity> entityClass){
        EntityRegistry.registerModEntity(entityClass, "ancientAddin." + id, EntityRegistry.findGlobalUniqueEntityId(), AncientAddin.ancientAddin, 10, 1, true);
    }

    static {
        registerEntity("drone", EntityDrone.class);
    }
}
