package net.yuri6037.AncientAddinMod.items.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.yuri6037.AncientAddinMod.entity.render.ModelDrone;

@SideOnly(Side.CLIENT)
public class ModelDroneRecharge extends ModelBase {

    private ModelDrone drone;
    private ModelRenderer mainBox;

    public ModelDroneRecharge() {
        textureWidth = 32;
        textureHeight = 32;

        drone = new ModelDrone();

        mainBox = new ModelRenderer(this, 0, 10);
        mainBox.addBox(-4.5F, 3.2F, -2.5F, 9, 5, 5);
        mainBox.setRotationPoint(0F, 16.13333F, 0.5F);
        mainBox.setTextureSize(32, 32);
        mainBox.mirror = true;
        setRotation(mainBox, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float rotationX, float rotationY, float rotationZ, float scale) {
        //mainBox.render(scale);
        drone.render(entity, f, f1, 0, 0, 0, scale);
        setRotationAngles(f, f1, 0, 0, 0, scale, entity);
        mainBox.render(scale);
        //drone1Body.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float rotationX, float rotationY, float rotationZ, float scale, Entity entity) {
        float rotateX = (float) Math.toRadians(rotationX);
        float rotateY = (float) Math.toRadians(rotationY);
        float rotateZ = (float) Math.toRadians(rotationZ);
        setRotation(mainBox, rotateX, rotateY, rotateZ);
    }
}
