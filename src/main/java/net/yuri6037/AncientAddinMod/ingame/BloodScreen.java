package net.yuri6037.AncientAddinMod.ingame;

import net.minecraft.util.ResourceLocation;

/**
 * Made by Yuri6037
 */
public class BloodScreen extends GuiIngameRenderer {

    private ResourceLocation location;

    public BloodScreen() {
        super("");

        location = new ResourceLocation("AncientAddinMod:textures/gui/bloodScreen.png");
    }

    public void renderGameOverlay() {
        if (mc.thePlayer.getHealth() <= 3.5) {
            drawScreenTexture(location);
        }
    }

    public void updateGameOverlay() {

    }
}
