package net.yuri6037.AncientAddinMod.blocks.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

/**
 * Made by Yuri6037 (UNFINISHED ; does nothing more just as some tests about block rendering)
 */
@SideOnly(Side.CLIENT)
public class CameraBlockRenderer extends BlockRenderer {

    public CameraBlockRenderer() {
        super(0, false);
    }

    public void doInventoryBlockRendering(RenderBlocks renderer, int metadata, Block b) {

    }

    public void doWorldBlockRendering(IBlockAccess world, int x, int y, int z, RenderBlocks renderer, Block b) {
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        renderer.renderStandardBlock(b, x, y, z);
        renderer.renderStandardBlock(b, x, y + 1, z);
    }
}
