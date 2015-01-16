package net.yuri6037.AncientAddinMod.ingame;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.items.ItemList;
import org.lwjgl.opengl.GL11;

public class HandRayonThrowerHUD extends GuiIngameRenderer{

    public HandRayonThrowerHUD() {
        super("");
    }

    public void renderGameOverlay() {
        if (mc.thePlayer == null || mc.thePlayer.getCurrentEquippedItem() == null){
            return;
        }
        if (this.mc.gameSettings.thirdPersonView == 0 && mc.thePlayer.getCurrentEquippedItem().getItem() == ItemList.rayonThrower && AncientsUtil.isPlayerAncient(mc.thePlayer)){
            ItemStack item = mc.thePlayer.getCurrentEquippedItem();
            if (item.stackTagCompound != null) {
                String str = item.stackTagCompound.getInteger("Battery") + " / 16";
                ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
                int k = scaledresolution.getScaledWidth();
                int l = scaledresolution.getScaledHeight();
                int txtWidth = mc.fontRenderer.getStringWidth(str);
                drawString(mc.fontRenderer, str, k / 2 - txtWidth / 2, l - 64, 0xFF0000);
                GL11.glColor4f(1, 1, 1, 1);
            }
        }
    }

    public void updateGameOverlay() {

    }
}
