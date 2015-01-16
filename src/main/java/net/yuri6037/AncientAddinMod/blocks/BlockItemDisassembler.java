package net.yuri6037.AncientAddinMod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.yuri6037.AncientAddinMod.AncientAddin;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityItemDisassembler;

public class BlockItemDisassembler extends BlockContainer{

    private IIcon side;
    private IIcon up;
    private IIcon down;

    protected BlockItemDisassembler() {
        super(Material.iron);
        setBlockTextureName("iron_block");
        setBlockName("itemDisassembler");
        setCreativeTab(AncientAddin.ancientAddinTab);

        setResistance(20F);
        setHardness(1.5F);
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityItemDisassembler();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        }
        //code to open gui explained later
        player.openGui(AncientAddin.ancientAddin, 0, world, x, y, z);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        if (s == 1){
            return up;
        } else if (s == 0){
            return down;
        } else {
            return side;
        }
    }

    public boolean isOpaqueCube(){
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registerer)
    {
        side = registerer.registerIcon("AncientAddinMod:decompiler_side");
        up = registerer.registerIcon("AncientAddinMod:decompiler_up");
        down = registerer.registerIcon("AncientAddinMod:decompiler_down");
    }
}
