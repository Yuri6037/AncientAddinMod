package net.yuri6037.AncientAddinMod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.yuri6037.AncientAddinMod.AncientAddin;
import net.yuri6037.AncientAddinMod.enums.ArmorEnums;
import net.yuri6037.AncientAddinMod.util.MathUtilities;

import java.util.Random;

public class ItemGlasses extends ItemAncientArmor implements IRechargableBattery {

    public ItemGlasses() {
        super(ArmorEnums.ANCIENT_GLASSES, 0);
        setMaxDamage(128);
        setUnlocalizedName("ancientGlasses");
        setCreativeTab(AncientAddin.ancientAddinTab);
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("ticks", 0);
        }
        stack.stackTagCompound.setInteger("ticks", stack.stackTagCompound.getInteger("ticks") + 1);
        if (stack.stackTagCompound.getInteger("ticks") >= 60){
            pumpItemBattery(stack);
            stack.stackTagCompound.setInteger("ticks", 0);
        }
    }

    private void pumpItemBattery(ItemStack stack){
        stack.setItemDamage(stack.getItemDamage() + loopForFirstNumber(1, 2));
    }

    private static int loopForFirstNumber(int min, int max){
        Random random = new Random();
        int i = MathUtilities.generateRandomInteger(min, max, random);
        if (!MathUtilities.isNumberFirst(i)){
            loopForFirstNumber(min, max);
        }
        return i;
    }

    public void rechargeItemBattery(ItemStack toRecharge, int amount) {
        toRecharge.setItemDamage(toRecharge.getItemDamage() + amount);
    }

    public int getMaximumBatteryValue() {
        return 128;
    }

    public int getCurrentBatteryValue(ItemStack toCheck) {
        return 128 - toCheck.getItemDamage();
    }

    public boolean canRechargeItem(ItemStack toCheck) {
        return true;
    }
}
