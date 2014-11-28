package net.yuri6037.AncientAddinMod.blocks.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Made by Yuri6037
 */
@SideOnly(Side.CLIENT)
public class BlockRenderList {
    static {
        new CameraBlockRenderer();
    }
}
