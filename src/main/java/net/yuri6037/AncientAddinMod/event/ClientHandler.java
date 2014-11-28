package net.yuri6037.AncientAddinMod.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.yuri6037.AncientAddinMod.ingame.BloodScreen;
import net.yuri6037.AncientAddinMod.ingame.IngameRendererHookList;

@SideOnly(Side.CLIENT)
@SuppressWarnings("unused")
public class ClientHandler {

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent event){
        IngameRendererHookList.onRender();
    }

    @SubscribeEvent
    public void updateGameOverlay(TickEvent event){
        if (event.type == TickEvent.Type.CLIENT){
            IngameRendererHookList.onUpdate();
        }
    }

    static {
        IngameRendererHookList.registerIngameHook(new BloodScreen());
    }
}
