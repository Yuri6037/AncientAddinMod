package net.yuri6037.AncientAddinMod.items;

import net.minecraft.item.ItemStack;

public interface IRechargableBattery {

    public void rechargeItemBattery(ItemStack toRecharge, int amount);
    public int getMaximumBatteryValue();
    public int getCurrentBatteryValue(ItemStack toCheck);
    public boolean canRechargeItem(ItemStack toCheck);
}
