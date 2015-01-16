package net.yuri6037.AncientAddinMod.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.yuri6037.AncientAddinMod.AncientAddin;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityBatteryRecharger;

public class BlockBatteryRecharger extends BlockContainer {

    protected BlockBatteryRecharger() {
        super(Material.iron);
        setBlockName("batteryRecharger");
        setBlockTextureName("iron_block");
        setCreativeTab(AncientAddin.ancientAddinTab);
        setBlockBounds(0.126F, 0.0F, 0.126F, 0.871F, 0.75F, 0.871F);

        setResistance(30F);
        setHardness(2.5F);
    }

    public boolean isOpaqueCube(){
        return false;
    }

    public int getRenderType() {
        return 128;
    }

    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityBatteryRecharger();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        }
        //code to open gui explained later
        player.openGui(AncientAddin.ancientAddin, 1, world, x, y, z);
        return true;
    }
}
