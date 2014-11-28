package net.yuri6037.AncientAddinMod.entity.render;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.yuri6037.AncientAddinMod.entity.EntityDrone;

@SideOnly(Side.CLIENT)
public class RenderList {

    private static void registerEntityRenderer(Class<? extends Entity> entityClass, Class<? extends Render> renderClass){
        try {
            RenderingRegistry.registerEntityRenderingHandler(entityClass, renderClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static {
        registerEntityRenderer(EntityDrone.class, RenderDrone.class);
    }
}
