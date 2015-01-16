package net.yuri6037.AncientAddinMod.blocks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerItemDisassembler extends Container {

    public ContainerItemDisassembler(InventoryPlayer inventoryPlayer, TileEntityItemDisassembler te){
        int i;
        int j;

        //Conatiner slots
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(te, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }

        //Inventory slots
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int id) {
        Slot slot = (Slot) this.inventorySlots.get(id);
        ItemStack stack = null;
        if (slot.getHasStack()) {
            if (id > 8) { //Transfer player->container
                ItemStack stack1 = slot.getStack();
                if (this.mergeItemStack(stack1, 0, 9, true)) {
                    stack = stack1;
                }
            } else { //Transfer continer->player
                ItemStack stack1 = slot.getStack();
                if (this.mergeItemStack(stack1, 9, this.inventorySlots.size(), true)) {
                    stack = stack1;
                }
            }
        }

        return stack;
    }
}
