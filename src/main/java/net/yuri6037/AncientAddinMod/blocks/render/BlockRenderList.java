package net.yuri6037.AncientAddinMod.blocks.render;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityBatteryRecharger;

/**
 * Made by Yuri6037
 */
@SideOnly(Side.CLIENT)
public class BlockRenderList {
    static {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBatteryRecharger.class, new TileEntityBatteryRechargerRenderer());
        new BatteryRechargerInventoryRenderer();
    }
}
