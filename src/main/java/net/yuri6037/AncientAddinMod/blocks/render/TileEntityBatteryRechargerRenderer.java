package net.yuri6037.AncientAddinMod.blocks.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityBatteryRecharger;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class TileEntityBatteryRechargerRenderer extends TileEntitySpecialRenderer {

    private ResourceLocation texture;
    private ModelBatteryRecharger blockModel;


    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public TileEntityBatteryRechargerRenderer() {
        texture = new ResourceLocation("AncientAddinMod:textures/entity/ModelBatteryRecharger.png");
        blockModel = new ModelBatteryRecharger();
    }

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
        TileEntityBatteryRecharger recharger = (TileEntityBatteryRecharger) te;
        ItemStack rechargingStack = recharger.getStackInSlot(1);

        glPushMatrix();
        glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        bindTexture(texture);

        //The block itself
        glPushMatrix();
        glRotatef(180F, 0.0F, 0.0F, 1.0F);
        if (recharger.isRechargingItem()) {
            blockModel.render(null, recharger.getRechargingRotationAngle(), 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        } else {
            blockModel.render(null, 0, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }
        glPopMatrix();
        glPopMatrix();

        //The recharging item rendering
        if (rechargingStack != null) {
            glPushMatrix();
            glTranslatef((float) x + 0.5F, (float) y + 0.65F, (float) z + 0.5F);
            glScalef(0.4F, 0.4F, 0.4F);
            renderDroppedItem(rechargingStack, rechargingStack.getIconIndex(), 1, 1, 1, 0);
            glPopMatrix();
        }
    }

    private void renderDroppedItem(ItemStack p_77020_1_, IIcon p_77020_2_, float p_77020_5_, float p_77020_6_, float p_77020_7_, int pass) {
        Tessellator tessellator = Tessellator.instance;

        if (p_77020_2_ == null) {
            TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
            ResourceLocation resourcelocation = texturemanager.getResourceLocation(p_77020_1_.getItemSpriteNumber());
            p_77020_2_ = ((TextureMap) texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
        }

        float f14 = (p_77020_2_).getMinU();
        float f15 = (p_77020_2_).getMaxU();
        float f4 = (p_77020_2_).getMinV();
        float f5 = (p_77020_2_).getMaxV();
        float f7 = 0.5F;
        float f8 = 0.25F;
        float f10;

        GL11.glPushMatrix();

        //GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);

        float f9 = 0.0625F;
        f10 = 0.021875F;
        //int j = p_77020_1_.stackSize;
        /*byte b0;

        if (j < 2) {
            b0 = 1;
        } else if (j < 16) {
            b0 = 2;
        } else if (j < 32) {
            b0 = 3;
        } else {
            b0 = 4;
        }*/

        GL11.glTranslatef(-f7, -f8, -((f9 + f10) * (float) 1 / 2.0F));

        // Makes items offset when in 3D, like when in 2D, looks much better. Considered a vanilla bug...
        GL11.glTranslatef(0f, 0f, f9 + f10);

        if (p_77020_1_.getItemSpriteNumber() == 0) {
            bindTexture(TextureMap.locationBlocksTexture);
        } else {
            bindTexture(TextureMap.locationItemsTexture);
        }

        GL11.glColor4f(p_77020_5_, p_77020_6_, p_77020_7_, 1.0F);
        ItemRenderer.renderItemIn2D(tessellator, f15, f4, f14, f5, (p_77020_2_).getIconWidth(), (p_77020_2_).getIconHeight(), f9);

        if (p_77020_1_.hasEffect(pass)) {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            bindTexture(RES_ITEM_GLINT);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            float f11 = 0.76F;
            GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float f12 = 0.125F;
            GL11.glScalef(f12, f12, f12);
            float f13 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(f13, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f12, f12, f12);
            f13 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-f13, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
        }
        glPopMatrix();
    }
}
