package net.yuri6037.AncientAddinMod.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.yuri6037.AncientAddinMod.enums.EntityType;

import java.util.ArrayList;
import java.util.List;

public class EntityFinder {

    /* What the hack have you done that Mojang !! I need world's Vec3Pool ! So why have you removed that ?! */
    public static final Vec3Pool worldVecPool = new Vec3Pool(300, 2000);

    public static List<Entity> findEntitiesInRayon(EntityType typeToFind, Entity e, int range, boolean needToBeSeen) {
        double px = e.posX;
        double py = e.posY;
        double pz = e.posZ;
        Class<? extends Entity> cl = null;
        switch (typeToFind) {
            case LIVING:
                cl = EntityLivingBase.class;
                break;
            case OBJECT:
                cl = Entity.class;
                break;
        }
        List l = e.worldObj.getEntitiesWithinAABB(cl, AxisAlignedBB.getBoundingBox(px - range, py - range, pz - range, px + range, py + range, pz + range));
        List<Entity> result = new ArrayList<Entity>();
        for (Object aL : l) {
            switch (typeToFind) {
                case LIVING:
                    EntityLivingBase x = (EntityLivingBase) aL;
                    if (x != null) {
                        if (x.getDistanceToEntity(e) <= range && (!needToBeSeen || x.canEntityBeSeen(e))) {
                            result.add(x);
                        }
                    }
                    break;
                case OBJECT:
                    Entity x1 = (Entity) aL;
                    if (x1 != null) {
                        if (x1.getDistanceToEntity(e) <= range) {
                            result.add(x1);
                        }
                    }
                    break;
            }
        }
        return result;
    }

    public static EntityLivingBase findLookedAtEntity(EntityPlayer player, int max_dis) {
        List list = EntityFinder.findEntitiesInRayon(EntityType.LIVING, player, max_dis, true);
        for (Object aList : list) {
            EntityLivingBase obj = (EntityLivingBase) aList;

            Vec3 vec3 = player.getLook(1.0F).normalize();

            Vec3 vec31 = worldVecPool.getVecFromPool(obj.posX - player.posX, obj.boundingBox.minY + (double) (obj.height / 2.0F) - (player.posY + (double) player.getEyeHeight()), obj.posZ - player.posZ);
            double d0 = vec31.lengthVector();
            vec31 = vec31.normalize();
            double d1 = vec3.dotProduct(vec31);
            if (d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(obj)) {
                return obj;
            }
        }
        return null;
    }


}
