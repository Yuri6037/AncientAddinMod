package net.yuri6037.AncientAddinMod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.yuri6037.AncientAddinMod.AncientAddin;

public abstract class ItemBatteryBase extends Item {

    public ItemBatteryBase(){
        setCreativeTab(AncientAddin.ancientAddinTab);
        setTextureName(getBatteryTexture());
        setMaxDamage(getMaxJuiceValue());
        setUnlocalizedName(getBatteryName());
    }

    public abstract int getMaxJuiceValue();

    public abstract String getBatteryTexture();

    public abstract String getBatteryName();

    public boolean canPump(ItemStack stack, int amount){
        int var = getMaxJuiceValue() - stack.getItemDamage();
        return var >= amount;
    }

    public ItemStack pump(ItemStack stack, int amount){
        //stack.damageItem(amount, null);
        stack.setItemDamage(stack.getItemDamage() + amount);
        if (stack.getItemDamage() >= getMaxJuiceValue()){
            return null;
        }
        return stack;
    }
}
