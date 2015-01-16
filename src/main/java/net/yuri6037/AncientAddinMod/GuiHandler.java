package net.yuri6037.AncientAddinMod;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.yuri6037.AncientAddinMod.blocks.gui.GuiBatteryRecharger;
import net.yuri6037.AncientAddinMod.blocks.gui.GuiItemDisassembler;
import net.yuri6037.AncientAddinMod.blocks.inventory.ContainerBatteryRecharger;
import net.yuri6037.AncientAddinMod.blocks.inventory.ContainerItemDisassembler;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityBatteryRecharger;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityItemDisassembler;

/**
 * The gui handler for this mod
 * \/ID Number Registry \/
 * 0 = Item Decompiler
 * 1 = Battery Recharger
 */
public class GuiHandler implements IGuiHandler {

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID){
            case 0:
                return new ContainerItemDisassembler(player.inventory, (TileEntityItemDisassembler) world.getTileEntity(x, y, z));
            case 1:
                return new ContainerBatteryRecharger(player.inventory, (TileEntityBatteryRecharger) world.getTileEntity(x, y, z));
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID) {
            case 0:
                return new GuiItemDisassembler(player.inventory, (TileEntityItemDisassembler) world.getTileEntity(x, y, z));
            case 1:
                return new GuiBatteryRecharger(player.inventory, (TileEntityBatteryRecharger) world.getTileEntity(x, y, z));
        }
        return null;
    }
}
