package net.yuri6037.AncientAddinMod.blocks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.yuri6037.AncientAddinMod.blocks.inventory.slots.SlotBattery;
import net.yuri6037.AncientAddinMod.blocks.inventory.slots.SlotRechargableItem;

public class ContainerBatteryRecharger extends Container {

    public ContainerBatteryRecharger(InventoryPlayer playerInv, TileEntityBatteryRecharger recharger){
        addSlotToContainer(new SlotBattery(recharger, 0, 50, 35));
        addSlotToContainer(new SlotRechargableItem(recharger, 1, 116, 35));

        //Inventory slots
        int i;
        int j;
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int id) {
        /*Slot slot = (Slot) this.inventorySlots.get(id);
        ItemStack stack = null;
        if (slot.getHasStack()) {
            if (id > 1) { //Transfer player->container
                ItemStack stack1 = slot.getStack();
                if (this.mergeItemStack(stack1, 0, 2, true)) {
                    stack = stack1;
                }
            } else { //Transfer continer->player
                ItemStack stack1 = slot.getStack();
                if (this.mergeItemStack(stack1, 2, this.inventorySlots.size(), true)) {
                    stack = stack1;
                }
            }
        }*/

        return null;
    }
}
