package net.yuri6037.AncientAddinMod.items.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Made by Yuri6037 with help of Techne
 */
@SideOnly(Side.CLIENT)
public class ModelDroneLauncher extends ModelBase {

    //fields
    private ModelRenderer launcherBase;
    private ModelRenderer thrower;

    //Animation vars
    private int ticks;
    public int angleDeg;

    public boolean reloading = false;

    public ModelDroneLauncher() {
        textureWidth = 64;
        textureHeight = 32;

        /* Base */
        launcherBase = new ModelRenderer(this, 0, 0);
        launcherBase.addBox(-2F, -2F, 0F, 4, 4, 2);
        launcherBase.setRotationPoint(0F, 21F, -6F);
        launcherBase.setTextureSize(64, 32);
        launcherBase.mirror = true;
        setRotation(launcherBase, 0F, 0F, 0F);

        /* Hand grip */
        ModelRenderer handPartA = new ModelRenderer(this, 16, 3);
        handPartA.addBox(0F, 0F, 0F, 4, 1, 2);
        handPartA.setRotationPoint(-2F, 22F, -8F);
        handPartA.setTextureSize(64, 32);
        handPartA.mirror = true;
        setRotation(handPartA, 0F, 0F, 0F);

        ModelRenderer handPartB = new ModelRenderer(this, 16, 0);
        handPartB.addBox(0F, 0F, 0F, 4, 1, 2);
        handPartB.setRotationPoint(-2F, 19F, -8F);
        handPartB.setTextureSize(64, 32);
        handPartB.mirror = true;
        setRotation(handPartB, 0F, 0F, 0F);

        ModelRenderer handPartC = new ModelRenderer(this, 24, 8);
        handPartC.addBox(0F, 0F, 0F, 1, 4, 2);
        handPartC.setRotationPoint(-2F, 19F, -8F);
        handPartC.setTextureSize(64, 32);
        handPartC.mirror = true;
        setRotation(handPartC, 0F, 0F, 0F);

        ModelRenderer handPartD = new ModelRenderer(this, 18, 8);
        handPartD.addBox(0F, 0F, 0F, 1, 4, 2);
        handPartD.setRotationPoint(1F, 19F, -8F);
        handPartD.setTextureSize(64, 32);
        handPartD.mirror = true;
        setRotation(handPartD, 0F, 0F, 0F);

        /* Thrower vars */
        thrower = new ModelRenderer(this, 0, 8);
        thrower.addBox(-1F, -1F, 0F, 2, 2, 7);
        thrower.setRotationPoint(0F, 21F, -4F);
        thrower.setTextureSize(64, 32);
        thrower.mirror = true;
        setRotation(thrower, 0F, 0F, 0F);

        ModelRenderer throwerTubeAA = new ModelRenderer(this, 58, 20);
        throwerTubeAA.addBox(0F, 0F, 0F, 2, 2, 1);
        throwerTubeAA.setRotationPoint(-1F, 18F, 0F);
        throwerTubeAA.setTextureSize(64, 32);
        throwerTubeAA.mirror = true;
        setRotation(throwerTubeAA, 0F, 0F, 0F);

        ModelRenderer throwerTubeAB = new ModelRenderer(this, 48, 13);
        throwerTubeAB.addBox(0F, 0F, 0F, 2, 1, 6);
        throwerTubeAB.setRotationPoint(-1F, 18F, -5F);
        throwerTubeAB.setTextureSize(64, 32);
        throwerTubeAB.mirror = true;
        setRotation(throwerTubeAB, 0F, 0F, 0F);

        ModelRenderer throwerTubeBA = new ModelRenderer(this, 58, 20);
        throwerTubeBA.addBox(0F, 0F, 0F, 2, 2, 1);
        throwerTubeBA.setRotationPoint(-1F, 22F, 0F);
        throwerTubeBA.setTextureSize(64, 32);
        throwerTubeBA.mirror = true;
        setRotation(throwerTubeBA, 0F, 0F, 0F);

        ModelRenderer throwerTubeBB = new ModelRenderer(this, 48, 13);
        throwerTubeBB.addBox(0F, 0F, 0F, 2, 1, 6);
        throwerTubeBB.setRotationPoint(-1F, 23F, -5F);
        throwerTubeBB.setTextureSize(64, 32);
        throwerTubeBB.mirror = true;
        setRotation(throwerTubeBB, 0F, 0F, 0F);

        ModelRenderer throwerTubeCA = new ModelRenderer(this, 58, 8);
        throwerTubeCA.addBox(0F, 0F, 0F, 2, 2, 1);
        throwerTubeCA.setRotationPoint(1F, 20F, 0F);
        throwerTubeCA.setTextureSize(64, 32);
        throwerTubeCA.mirror = true;
        setRotation(throwerTubeCA, 0F, 0F, 0F);

        ModelRenderer throwerTubeCB = new ModelRenderer(this, 50, 0);
        throwerTubeCB.addBox(0F, 0F, 0F, 1, 2, 6);
        throwerTubeCB.setRotationPoint(2F, 20F, -5F);
        throwerTubeCB.setTextureSize(64, 32);
        throwerTubeCB.mirror = true;
        setRotation(throwerTubeCB, 0F, 0F, 0F);

        ModelRenderer throwerTubeDB = new ModelRenderer(this, 50, 0);
        throwerTubeDB.addBox(0F, 0F, 0F, 1, 2, 6);
        throwerTubeDB.setRotationPoint(-3F, 20F, -5F);
        throwerTubeDB.setTextureSize(64, 32);
        throwerTubeDB.mirror = true;
        setRotation(throwerTubeDB, 0F, 0F, 0F);

        ModelRenderer throwerTubeDA = new ModelRenderer(this, 58, 8);
        throwerTubeDA.addBox(0F, 0F, 0F, 2, 2, 1);
        throwerTubeDA.setRotationPoint(-3F, 20F, 0F);
        throwerTubeDA.setTextureSize(64, 32);
        throwerTubeDA.mirror = true;
        setRotation(throwerTubeDA, 0F, 0F, 0F);

        /* Parent hand grip to base */
        convertToChild(launcherBase, handPartA);
        convertToChild(launcherBase, handPartB);
        convertToChild(launcherBase, handPartC);
        convertToChild(launcherBase, handPartD);

        /* Parent thrower tubes to thrower base */
        convertToChild(thrower, throwerTubeAA);
        convertToChild(thrower, throwerTubeAB);
        convertToChild(thrower, throwerTubeBA);
        convertToChild(thrower, throwerTubeBB);
        convertToChild(thrower, throwerTubeCA);
        convertToChild(thrower, throwerTubeCB);
        convertToChild(thrower, throwerTubeDA);
        convertToChild(thrower, throwerTubeDB);


        convertToChild(launcherBase, thrower);
    }

    public void render(Entity entity, float f, float f1, float rotationX, float rotationY, float rotationZ, float scale) {
        setRotationAngles(f, f1, rotationX, rotationY, rotationZ, scale, entity);
        launcherBase.render(scale);
        //thrower.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
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

    public void setRotationAngles(float f, float f1, float rotationX, float rotationY, float rotationZ, float scale, Entity entity) {
        if (reloading) {
            ticks++;
            if (ticks >= 1) {
                angleDeg += 5;
                ticks = 0;
                if (angleDeg >= 360) {
                    angleDeg = 0;
                }
            }
        }

        float z = (float) Math.toRadians(angleDeg);

        float rotateX = (float) Math.toRadians(rotationX);
        float rotateY = (float) Math.toRadians(rotationY);
        float rotateZ = (float) Math.toRadians(rotationZ);
        setRotation(launcherBase, rotateX, rotateY, rotateZ);
        setRotation(thrower, 0, 0, z);
    }
}
