package net.yuri6037.AncientAddinMod.items.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelAncientRayonThrower extends ModelBase {

    //fields
    private ModelRenderer thrower;
    private ModelRenderer grip;

    public ModelAncientRayonThrower()
    {
        textureWidth = 32;
        textureHeight = 32;

        thrower = new ModelRenderer(this, 0, 0);
        thrower.addBox(-0.5F, -0.5F, 0F, 1, 1, 7);
        thrower.setRotationPoint(0F, 19.5F, -2F);
        thrower.setTextureSize(64, 32);
        thrower.mirror = true;
        setRotation(thrower, 0.0174533F, 0F, 0.7853982F);
        grip = new ModelRenderer(this, 0, 8);
        grip.addBox(0F, 0F, 0F, 1, 4, 1);
        grip.setRotationPoint(-0.5F, 19F, -2F);
        grip.setTextureSize(64, 32);
        grip.mirror = true;
        setRotation(grip, 0F, 0F, 0F);

        convertToChild(grip, thrower);
    }

    private void convertToChild(ModelRenderer parParent, ModelRenderer parChild) {
        parChild.rotationPointX -= parParent.rotationPointX;
        parChild.rotationPointY -= parParent.rotationPointY;
        parChild.rotationPointZ -= parParent.rotationPointZ;
        parChild.rotateAngleX -= parParent.rotateAngleX;
        parChild.rotateAngleY -= parParent.rotateAngleY;
        parChild.rotateAngleZ -= parParent.rotateAngleZ;
        parParent.addChild(parChild);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        grip.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        float rotateX = (float) Math.toRadians(f2);
        float rotateY = (float) Math.toRadians(f3);
        float rotateZ = (float) Math.toRadians(f4);
        setRotation(grip, rotateX, rotateY, rotateZ);
    }

}
