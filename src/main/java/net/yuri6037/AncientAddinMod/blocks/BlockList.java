package net.yuri6037.AncientAddinMod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.tileentity.TileEntity;
import net.yuri6037.AncientAddinMod.AncientAddin;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityBatteryRecharger;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityItemDisassembler;

public class BlockList {

    public static final Block stazeModuleCore;
    public static final Block itemDissasembler;
    public static final Block blackDiamond;

    public static final Block batteryRecharger;

    private static Block registerBlock(String id, Block b){
        GameRegistry.registerBlock(b, "ancientAddin." + id);
        return b;
    }

    private static Block addTileEntity(Block b, Class<? extends TileEntity> cl, String id){
        GameRegistry.registerTileEntity(cl, "ancientAddin." + id);
        return b;
    }

    static {
        stazeModuleCore = registerBlock("stazeModuleCore", new BlockStazeModuleCore());
        itemDissasembler = registerBlock("itemDisassembler", new BlockItemDisassembler());
        blackDiamond = registerBlock("blackDiamond", new BlockCompressed(MapColor.blackColor)).setBlockTextureName("AncientAddinMod:black_diamond").setBlockName("blackDiamond").setCreativeTab(AncientAddin.ancientAddinTab);
        batteryRecharger = registerBlock("batteryRecharger", new BlockBatteryRecharger());

        addTileEntity(itemDissasembler, TileEntityItemDisassembler.class, "itemDisassembler");
        addTileEntity(batteryRecharger, TileEntityBatteryRecharger.class, "batteryRecharger");
    }
}
