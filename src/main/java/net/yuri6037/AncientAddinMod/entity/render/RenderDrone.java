package net.yuri6037.AncientAddinMod.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.yuri6037.AncientAddinMod.entity.EntityDrone;
import org.lwjgl.opengl.GL11;

/**
 * Made by Yuri6037
 */
@SideOnly(Side.CLIENT)
public class RenderDrone extends Render {

    private ResourceLocation location = new ResourceLocation("AncientAddinMod:textures/entity/ModelDrone.png");

    private ModelDrone droneModel;

    public RenderDrone(){
        droneModel = new ModelDrone();
    }

    public void doRender(Entity entity, double x, double y, double z, float pitch, float yaw) {
        if (entity == null){
            return;
        }
        if (!(entity instanceof EntityDrone)){
            return;
        }
        GL11.glPushMatrix();
            bindTexture(getEntityTexture(entity));
            GL11.glTranslated(x, y, z);
            GL11.glRotatef(entity.rotationYaw, 0, 1, 0);
            GL11.glRotatef(entity.rotationPitch, 0, 0, 1);
            droneModel.render(entity, 0.0F, 0.0F, 0.0F, (float)entity.rotationYaw, (float)entity.rotationPitch, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return location;
    }
}
