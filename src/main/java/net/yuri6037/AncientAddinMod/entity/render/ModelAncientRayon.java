package net.yuri6037.AncientAddinMod.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelAncientRayon extends ModelBase{

    //fields
    private ModelRenderer rayon;

    //Animation vars
    private int ticks;
    private int angleDeg;

    public ModelAncientRayon() {
        textureWidth = 32;
        textureHeight = 32;

        rayon = new ModelRenderer(this, 0, 0);
        rayon.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
        rayon.setRotationPoint(0F, 23F, 0F);
        rayon.setTextureSize(32, 32);
        rayon.mirror = true;
        setRotation(rayon, 1.570796F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        rayon.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        ticks++;
        if (ticks >= 0){
            angleDeg += 5;
            ticks = 0;
            if (angleDeg >= 360){
                angleDeg = 0;
            }
        }

        setRotation(rayon, (float) Math.toRadians(angleDeg), 0, 0);
    }
}
