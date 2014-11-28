package net.yuri6037.AncientAddinMod.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Made by Yuri6037 with help of Techne
 */
@SideOnly(Side.CLIENT)
public class ModelDrone extends ModelBase {

    //fields
    private ModelRenderer droneBody;

    //Animation vars
    private int ticks;
    private int angleDeg;

    public ModelDrone() {
        textureWidth = 32;
        textureHeight = 32;

        droneBody = new ModelRenderer(this, 0, 0);
        droneBody.addBox(0F, -1.5F, -1.5F, 3, 3, 3);
        droneBody.setRotationPoint(-8F, 22.5F, 0.5F);
        droneBody.setTextureSize(32, 32);
        droneBody.mirror = true;
        setRotation(droneBody, 0F, 0F, 0F);

        ModelRenderer barCenter = new ModelRenderer(this, 0, 6);
        barCenter.addBox(0F, -0.5F, -0.5F, 4, 1, 1);
        barCenter.setRotationPoint(-5F, 22.5F, 0.5F);
        barCenter.setTextureSize(32, 32);
        barCenter.mirror = true;
        setRotation(barCenter, 0F, 0F, 0F);

        ModelRenderer barA = new ModelRenderer(this, 12, 4);
        barA.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
        barA.setRotationPoint(-5F, 23.5F, -0.5F);
        barA.setTextureSize(32, 32);
        barA.mirror = true;
        setRotation(barA, 0.7853982F, 0F, 0F);

        ModelRenderer barB = new ModelRenderer(this, 12, 6);
        barB.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
        barB.setRotationPoint(-5F, 23.5F, 1.5F);
        barB.setTextureSize(32, 32);
        barB.mirror = true;
        setRotation(barB, 0.7853982F, 0F, 0F);

        ModelRenderer barD = new ModelRenderer(this, 12, 0);
        barD.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
        barD.setRotationPoint(-5F, 21.5F, 1.5F);
        barD.setTextureSize(32, 32);
        barD.mirror = true;
        setRotation(barD, 0.7853982F, 0F, 0F);

        ModelRenderer barC = new ModelRenderer(this, 12, 2);
        barC.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
        barC.setRotationPoint(-5F, 21.5F, -0.5F);
        barC.setTextureSize(32, 32);
        barC.mirror = true;
        setRotation(barC, 0.7853982F, 0F, 0F);

        convertToChild(droneBody, barCenter);
        convertToChild(droneBody, barA);
        convertToChild(droneBody, barB);
        convertToChild(droneBody, barC);
        convertToChild(droneBody, barD);
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

    //f5 = sclale
    //f2, f3, f4 = rotations angles
    public void render(Entity entity, float f, float f1, float rotationX, float rotationY, float rotationZ, float scale) {
        setRotationAngles(f, f1, rotationX, rotationY, rotationZ, scale, entity);
        droneBody.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float rotationX, float rotationY, float rotationZ, float scale, Entity entity) {
        ticks++;
        if (ticks >= 0){
            angleDeg += 5;
            ticks = 0;
            if (angleDeg >= 360){
                angleDeg = 0;
            }
        }

        setRotation(droneBody, (float) Math.toRadians(angleDeg), 0, 0);
    }
}
