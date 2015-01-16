package net.yuri6037.AncientAddinMod.items.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.yuri6037.AncientAddinMod.items.ItemList;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDroneRecharge implements IItemRenderer {

    private ModelDroneRecharge itemInventoryModel;
    private ResourceLocation location;

    public RenderDroneRecharge(){
        itemInventoryModel = new ModelDroneRecharge();
        location = new ResourceLocation("AncientAddinMod:textures/entity/ModelDroneRecharge.png");
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.ENTITY || type == ItemRenderType.INVENTORY;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return item.getItem() == ItemList.droneRecharge;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch(type){
            case EQUIPPED:
                GL11.glPushMatrix();
                GL11.glTranslatef(0, -1, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                itemInventoryModel.render(null, 0, 0, 0, 0, 0, 0.1F);
                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();
                GL11.glTranslatef(0, -1, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                itemInventoryModel.render(null, 0, 0, 0, 0, 0, 0.1F);
                GL11.glPopMatrix();
                break;
            case ENTITY:
                GL11.glPushMatrix();
                GL11.glTranslatef(0, -2, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                itemInventoryModel.render(null, 0, 0, 0, 0, 0, 0.1F);
                GL11.glPopMatrix();
                break;
            case INVENTORY:
                GL11.glPushMatrix();
                GL11.glTranslatef(0, -2, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(location);
                itemInventoryModel.render(null, 0, 0, 0, 0, 0, 0.1F);
                GL11.glPopMatrix();
                break;
            default:
                break;
        }
    }
}
