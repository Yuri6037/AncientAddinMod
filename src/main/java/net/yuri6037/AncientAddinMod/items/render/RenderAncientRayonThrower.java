package net.yuri6037.AncientAddinMod.items.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.yuri6037.AncientAddinMod.items.ItemList;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderAncientRayonThrower implements IItemRenderer {

    private ModelAncientRayonThrower weaponModel;
    private ModelAncientRayonThrower weaponInventoryModel;
    private ResourceLocation location;

    public RenderAncientRayonThrower(){
        weaponModel = new ModelAncientRayonThrower();
        weaponInventoryModel = new ModelAncientRayonThrower();
        location = new ResourceLocation("AncientAddinMod:textures/entity/ModelAncientRayonThrower.png");
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.ENTITY || type == ItemRenderType.INVENTORY;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return item.getItem() == ItemList.rayonThrower;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch(type){
            case EQUIPPED:
                /*if (item.stackTagCompound != null && item.stackTagCompound.getInteger("shootTime") > 0 && item.stackTagCompound.getBoolean("firstShoot")){
                    weaponModel.reloading = true;
                } else {
                    weaponModel.reloading = false;
                    weaponModel.angleDeg = 0;
                }*/
                GL11.glPushMatrix();
                GL11.glTranslatef(1F, -4.3F, 1.5F);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                weaponModel.render((Entity)data[1], 0, 0, 180, 50, 360, 0.3F);
                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:
                /*if (item.stackTagCompound != null && item.stackTagCompound.getInteger("shootTime") > 0 && item.stackTagCompound.getBoolean("firstShoot")){
                    weaponModel.reloading = true;
                } else {
                    weaponModel.reloading = false;
                    weaponModel.angleDeg = 0;
                }*/
                GL11.glPushMatrix();
                GL11.glTranslatef(1F, -4.3F, 1.5F);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                weaponModel.render((Entity)data[1], 0, 0, 180, -50, 360, 0.3F);
                GL11.glPopMatrix();
                break;
            case ENTITY:
                GL11.glPushMatrix();
                GL11.glTranslatef(0, -1.5F, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                weaponInventoryModel.render((Entity)data[1], 0, 0, 180, -50, 360, 0.1F);
                GL11.glPopMatrix();
                break;
            case INVENTORY:
                GL11.glPushMatrix();
                GL11.glTranslatef(0.2F, -3.3F, 1.5F);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                weaponInventoryModel.render(null, 0, 0, 180, -50, 360, 0.2F);
                GL11.glPopMatrix();
                break;
            default:
                break;
        }
    }
}
