package net.yuri6037.AncientAddinMod.ingame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.yuri6037.AncientAddinMod.items.ItemList;

@SideOnly(Side.CLIENT)
public class HandDroneLauncherHUD extends GuiIngameRenderer{

    public HandDroneLauncherHUD() {
        super("");
    }

    public void renderGameOverlay() {
        if (mc.thePlayer.getCurrentEquippedItem().getItem() == ItemList.droneLauncher){
            ItemStack item = mc.thePlayer.getCurrentEquippedItem();
            if (item.stackTagCompound != null) {
               String str = item.stackTagCompound.getInteger("Battery") + " / 64";
               int txtWidth = mc.fontRenderer.getStringWidth(str);
               mc.fontRenderer.drawString(str, mc.displayWidth / 2 - txtWidth / 2, mc.displayHeight - 128, 0);
            }
        }
    }

    public void updateGameOverlay() {

    }
}
