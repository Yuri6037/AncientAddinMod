package net.yuri6037.AncientAddinMod.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.yuri6037.AncientAddinMod.entity.EntityAncientRayon;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderAncientRayon extends Render {

    private ResourceLocation location = new ResourceLocation("AncientAddinMod:textures/entity/ModelAncientRayon.png");
    private ModelAncientRayon rayonModel = new ModelAncientRayon();

    public void doRender(Entity entity, double x, double y, double z, float pitch, float yaw) {
        if (entity == null){
            return;
        }
        if (!(entity instanceof EntityAncientRayon)){
            return;
        }
        GL11.glPushMatrix();
            bindTexture(getEntityTexture(entity));
            GL11.glTranslated(x, y, z);
            GL11.glRotatef(entity.rotationYaw, 0, 1, 0);
            GL11.glRotatef(entity.rotationPitch, 0, 0, 1);
            rayonModel.render(entity, 0.0F, 0.0F, 0.0F, entity.rotationYaw, entity.rotationPitch, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return location;
    }
}
