package net.yuri6037.AncientAddinMod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.yuri6037.AncientAddinMod.blocks.render.CameraBlockRenderer;

/**
 * Made by Yuri6037 (UNFINISHED ; does nothing)
 */
public class BlockCamera extends Block {

    protected BlockCamera() {
        super(Material.iron);
        setCreativeTab(CreativeTabs.tabRedstone);
        setBlockName("camera");
        setBlockTextureName("iron");
    }

    public int getRenderType() {
        return 128;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
}
