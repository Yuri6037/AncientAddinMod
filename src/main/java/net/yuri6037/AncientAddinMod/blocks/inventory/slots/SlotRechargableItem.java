package net.yuri6037.AncientAddinMod.blocks.inventory.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.yuri6037.AncientAddinMod.items.IRechargableBattery;

public class SlotRechargableItem extends Slot {

    public SlotRechargableItem(IInventory inv, int id, int x, int y) {
        super(inv, id, x, y);
    }

    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof IRechargableBattery;
    }
}
