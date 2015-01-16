package net.yuri6037.AncientAddinMod.blocks.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelBatteryRecharger extends ModelBase {

    //fields
    private ModelRenderer base;
    private ModelRenderer mainPillar;

    public ModelBatteryRecharger() {
        textureWidth = 64;
        textureHeight = 64;

        base = new ModelRenderer(this, 0, 0);
        base.addBox(-6F, 0F, -6F, 12, 1, 12);
        base.setRotationPoint(0F, 23F, 0F);
        base.setTextureSize(64, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        mainPillar = new ModelRenderer(this, 0, 13);
        mainPillar.addBox(-1F, -8F, -1F, 2, 8, 2);
        mainPillar.setRotationPoint(0F, 23F, 0F);
        mainPillar.setTextureSize(64, 64);
        mainPillar.mirror = true;
        setRotation(mainPillar, 0F, 0F, 0F);
        ModelRenderer itemPinceA = new ModelRenderer(this, 12, 13);
        itemPinceA.addBox(-0.5F, 0F, 0F, 1, 2, 1);
        itemPinceA.setRotationPoint(0F, 14F, -1.5F);
        itemPinceA.setTextureSize(64, 64);
        itemPinceA.mirror = true;
        setRotation(itemPinceA, 0F, 0F, 0F);
        ModelRenderer itemPinceB = new ModelRenderer(this, 8, 16);
        itemPinceB.addBox(-0.5F, 0F, -1F, 1, 2, 1);
        itemPinceB.setRotationPoint(0F, 14F, 1.5F);
        itemPinceB.setTextureSize(64, 64);
        itemPinceB.mirror = true;
        setRotation(itemPinceB, 0F, 0F, 0F);
        ModelRenderer itemPinceC = new ModelRenderer(this, 8, 13);
        itemPinceC.addBox(0F, 0F, -0.5F, 1, 2, 1);
        itemPinceC.setRotationPoint(-1.5F, 14F, 0F);
        itemPinceC.setTextureSize(64, 64);
        itemPinceC.mirror = true;
        setRotation(itemPinceC, 0F, 0F, 0F);
        ModelRenderer itemPinceD = new ModelRenderer(this, 8, 19);
        itemPinceD.addBox(-1F, 0F, -0.5F, 1, 2, 1);
        itemPinceD.setRotationPoint(1.5F, 14F, 0F);
        itemPinceD.setTextureSize(64, 64);
        itemPinceD.mirror = true;
        setRotation(itemPinceD, 0F, 0F, 0F);

        convertToChild(mainPillar, itemPinceA);
        convertToChild(mainPillar, itemPinceB);
        convertToChild(mainPillar, itemPinceC);
        convertToChild(mainPillar, itemPinceD);

        convertToChild(base, mainPillar);
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

    public void render(Entity entity, float pillarRotation, float f1, float rotationX, float rotationY, float rotationZ, float scale) {
        setRotationAngles(pillarRotation, f1, rotationX, rotationY, rotationZ, scale, entity);
        base.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float pillarRotation, float f1, float rotationX, float rotationY, float rotationZ, float scale, Entity entity) {

        float z = (float) Math.toRadians(pillarRotation);

        float rotateX = (float) Math.toRadians(rotationX);
        float rotateY = (float) Math.toRadians(rotationY);
        float rotateZ = (float) Math.toRadians(rotationZ);
        setRotation(base, rotateX, rotateY, rotateZ);
        setRotation(mainPillar, 0, z, 0);
    }

}
