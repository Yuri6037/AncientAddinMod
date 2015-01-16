package net.yuri6037.AncientAddinMod.items;

import net.minecraft.item.Item;
import net.yuri6037.AncientAddinMod.AncientAddin;

public class ItemDroneRecharge extends Item {

    public ItemDroneRecharge(){
        setMaxStackSize(4);
        setUnlocalizedName("droneRecharge");
        setCreativeTab(AncientAddin.ancientAddinTab);
    }
}
