package net.yuri6037.AncientAddinMod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockStazeModuleCore extends Block {

    private IIcon sideIcon;
    private IIcon upIcon;

    protected BlockStazeModuleCore() {
        super(Material.iron);
        setCreativeTab(CreativeTabs.tabRedstone);
        setBlockName("stazeModuleCore");
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block theBlock) {
        if (world.isBlockIndirectlyGettingPowered(x, y, z)){
            world.setBlock(x, y + 1, z, Blocks.ice);
            world.setBlock(x, y + 2, z, Blocks.ice);
            world.setBlock(x, y + 3, z, Blocks.ice);
        } else {
            world.setBlock(x, y + 1, z, Blocks.air);
            world.setBlock(x, y + 2, z, Blocks.air);
            world.setBlock(x, y + 3, z, Blocks.air);
        }
    }

    public void onBlockAdded(World world, int x, int y, int z) {

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? upIcon : sideIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registerer)
    {
        sideIcon = registerer.registerIcon("iron_block");
        upIcon = registerer.registerIcon("diamond_block");
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block b = world.getBlock(x + 1, y, z);
        Block b1 = world.getBlock(x - 1, y, z);
        Block b2 = world.getBlock(x, y, z + 1);
        Block b3 = world.getBlock(x, y, z - 1);
        Block b4 = world.getBlock(x - 1, y, z - 1);
        Block b5 = world.getBlock(x + 1, y, z + 1);
        return b == Blocks.iron_block && b1 == Blocks.iron_block && b2 == Blocks.iron_block && b3 == Blocks.iron_block && b4 == Blocks.iron_block && b5 == Blocks.iron_block;
    }

    /**
     * Called when the block is placed in the world.
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block b = world.getBlock(x + 1, y, z);
        Block b1 = world.getBlock(x - 1, y, z);
        Block b2 = world.getBlock(x, y, z + 1);
        Block b3 = world.getBlock(x, y, z - 1);
        Block b4 = world.getBlock(x - 1, y, z - 1);
        Block b5 = world.getBlock(x + 1, y, z + 1);
        return b == Blocks.iron_block && b1 == Blocks.iron_block && b2 == Blocks.iron_block && b3 == Blocks.iron_block && b4 == Blocks.iron_block && b5 == Blocks.iron_block;
    }
}
