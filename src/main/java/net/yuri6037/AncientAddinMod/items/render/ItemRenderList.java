package net.yuri6037.AncientAddinMod.items.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;
import net.yuri6037.AncientAddinMod.items.ItemList;

/**
 * Made by Yuri6037
 */
@SideOnly(Side.CLIENT)
public class ItemRenderList {

    static {
        MinecraftForgeClient.registerItemRenderer(ItemList.droneLauncher, new RenderDroneLauncher());
    }
}
