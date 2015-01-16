package net.yuri6037.AncientAddinMod.ingame;

import net.minecraft.client.gui.GuiIngame;

import java.util.ArrayList;
import java.util.List;

/**
 * Made by Yuri6037
 */
public class IngameRendererHookList {

    private static List<GuiIngameRenderer> renderersMap = new ArrayList<GuiIngameRenderer>();
    public static void registerIngameHook(GuiIngameRenderer renderer){
        renderersMap.add(renderer);
    }

    public static void onRender(){
        for (GuiIngameRenderer render : renderersMap){
            render.renderGameOverlay();
            render.mc.renderEngine.bindTexture(GuiIngame.icons);
        }
    }

    public static void onUpdate(){
        for (GuiIngameRenderer render : renderersMap){
            render.updateGameOverlay();
        }
    }
}
