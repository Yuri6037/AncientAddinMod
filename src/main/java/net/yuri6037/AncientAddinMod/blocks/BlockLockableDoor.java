package net.yuri6037.AncientAddinMod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.yuri6037.AncientAddinMod.AncientAddin;

public class BlockLockableDoor extends Block {

    protected BlockLockableDoor() {
        super(Material.iron);
        setBlockName("lockableDoor");
        setBlockTextureName("iron_block");
        setCreativeTab(AncientAddin.ancientAddinTab);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block theBlock) {
        if (world.isBlockIndirectlyGettingPowered(x, y, z)){
            for (int i = x ; i < 16 ; i--) {
                for (int j = y ; j < 16 ; j++) {
                    Block b = world.getBlock(i, j, z);
                    if (b == Blocks.air) {
                        world.setBlock(i, j, z, Blocks.wool, 3, 2);
                    }
                }
            }
        } else {
            for (int i = x ; i < 16 ; i--) {
                for (int j = y ; j < 16 ; j++) {
                    Block b = world.getBlock(i, j, z);
                    int meta = world.getBlockMetadata(i, j, z);
                    if (b == Blocks.wool && meta == 3) {
                        world.setBlockToAir(i, j, z);
                    }
                }
            }
        }
    }
}
