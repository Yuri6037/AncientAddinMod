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

/**
 * Made by Yuri6037
 */
@SideOnly(Side.CLIENT)
public class RenderDroneLauncher implements IItemRenderer{

    private ModelDroneLauncher weaponModel;
    private ModelDroneLauncher weaponInventoryModel;
    private ResourceLocation location;

    public RenderDroneLauncher(){
        weaponModel = new ModelDroneLauncher();
        weaponInventoryModel = new ModelDroneLauncher();
        location = new ResourceLocation("AncientAddinMod:textures/entity/ModelDroneLauncher.png");
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.ENTITY || type == ItemRenderType.INVENTORY;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return item.getItem() == ItemList.droneLauncher;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch(type){
            case EQUIPPED:
                if (item.stackTagCompound != null && item.stackTagCompound.getInteger("shootTime") > 0 && item.stackTagCompound.getBoolean("firstShoot")){
                    weaponModel.reloading = true;
                } else {
                    weaponModel.reloading = false;
                    weaponModel.angleDeg = 0;
                }
                GL11.glPushMatrix();
                GL11.glTranslatef(1, -6, 3);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                weaponModel.render((Entity)data[1], 0, 0, 180, 40, 0, 0.3F);
                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:
                if (item.stackTagCompound != null && item.stackTagCompound.getInteger("shootTime") > 0 && item.stackTagCompound.getBoolean("firstShoot")){
                    weaponModel.reloading = true;
                } else {
                    weaponModel.reloading = false;
                    weaponModel.angleDeg = 0;
                }
                GL11.glPushMatrix();
                GL11.glTranslatef(1, -6, 3);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                weaponModel.render((Entity)data[1], 0, 0, 0, 150, 0, 0.3F);
                GL11.glPopMatrix();
                break;
            case ENTITY:
                GL11.glPushMatrix();
                GL11.glTranslatef(0, -2, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                weaponInventoryModel.render(null, 0, 0, 0, 150, 0, 0.1F);
                GL11.glPopMatrix();
                break;
            case INVENTORY:
                GL11.glPushMatrix();
                GL11.glTranslatef(2, -0.5F, 3);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                weaponInventoryModel.render(null, 0, 0, 0, 150, 0, 0.1F);
                GL11.glPopMatrix();
                break;
            default:
                break;
        }
    }
}
