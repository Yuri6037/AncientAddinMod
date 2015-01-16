package net.yuri6037.AncientAddinMod.ingame;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.items.ItemList;
import org.lwjgl.opengl.GL11;

public class PersonalShieldHUD extends GuiIngameRenderer {

    public PersonalShieldHUD() {
        super("");
    }

    public void renderGameOverlay() {
        ItemStack stack = mc.thePlayer.inventory.getStackInSlot(8);
        if (this.mc.gameSettings.thirdPersonView == 0 && stack != null && stack.getItem() == ItemList.personalShield && AncientsUtil.isPlayerAncient(mc.thePlayer)){
            ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            int j = resolution.getScaledWidth();
            int k = resolution.getScaledHeight();
            int percent = (100 - ((stack.getItemDamage() * 100) / 256));
            int height = (128 - ((stack.getItemDamage() * 128) / 256));
            if (percent < 40 && percent > 20){
                drawScreenAlphaColoredRect(j - 27, k / 2 - 64, 22, 128, new int[]{0, 0, 0, 16});
                drawScreenAlphaColoredRect(j - 27, k / 2 - 64, 22, height, new int[]{255, 212, 133, 16});
            } else if (percent < 20){
                drawScreenAlphaColoredRect(j - 27, k / 2 - 64, 22, 128, new int[]{0, 0, 0, 16});
                drawScreenAlphaColoredRect(j - 27, k / 2 - 64, 22, height, new int[]{255, 133, 133, 16});
            } else {
                drawScreenAlphaColoredRect(j - 27, k / 2 - 64, 22, 128, new int[]{0, 0, 0, 16});
                drawScreenAlphaColoredRect(j - 27, k / 2 - 64, 22, height, new int[]{255, 255, 255, 16});
            }
            drawString(mc.fontRenderer, percent + "%",  j - 27, k / 2, 0xFF0000);
            GL11.glColor4f(1, 1, 1, 1);
        }
    }

    public void updateGameOverlay() {
    }
}
