package net.yuri6037.AncientAddinMod.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.util.MathUtilities;

import java.util.List;
import java.util.Random;

public class ItemPersonalShield extends Item implements IRechargableBattery {

    private IIcon activeTexture;
    private IIcon nonActiveTexture;

    public ItemPersonalShield(){
        setUnlocalizedName("personalShield");
        setTextureName("AncientAddinMod:shield_desactivated");
        setMaxStackSize(1);
        setMaxDamage(256);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (stack.stackTagCompound != null){
            int i = stack.getItemDamage();
            boolean b = stack.stackTagCompound.getBoolean("Activated");
            if (b){
                list.add("Shield is activated");
            } else {
                list.add("Shield is desactivated");
            }

            float percent = ((i * 100) / 256);
            list.add("Battery % : " + (100 - percent));
        }
    }

    public void registerIcons(IIconRegister register) {
        activeTexture = register.registerIcon("AncientAddinMod:shield_activated");
        nonActiveTexture = register.registerIcon("AncientAddinMod:shield_desactivated");
    }

    public IIcon getIconIndex(ItemStack stack) {
        if (stack.stackTagCompound != null){
            int i = stack.getItemDamage();
            if (stack.stackTagCompound.getBoolean("Activated") && i < 256){
                return activeTexture;
            } else {
                return nonActiveTexture;
            }
        }
        return nonActiveTexture;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (world.isRemote){ return false; }

        if (!AncientsUtil.isPlayerAncient(player)){
            return false;
        }

        if (stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("Activated", false);
        }
        if (stack.stackTagCompound.getBoolean("Activated")){
            stack.stackTagCompound.setBoolean("Activated", false);
        } else {
            stack.stackTagCompound.setBoolean("Activated", true);
        }
        return true;
    }

    public static boolean canPlayerTakeDamage(EntityPlayer player, DamageSource source){
        if (player.worldObj.isRemote){ return true; }

        if (source.getDamageType() != null && source.getDamageType().equals("drown") || source.getDamageType() != null && source.getDamageType().equals("starve")){
            return true;
        }
        ItemStack stack = player.inventory.getStackInSlot(8);
        if (stack != null && stack.getItem() == ItemList.personalShield){
            if (stack.getItemDamage() < 256){
                if (stack.stackTagCompound != null && stack.stackTagCompound.getBoolean("Activated")){
                    return false;
                }
            }
        }
        return true;
    }

    private static int loopForFirstNumber(boolean b){
        Random random = new Random();
        int min = 1;
        if (b){
            min = 5;
        }
        int i = MathUtilities.generateRandomInteger(min, 10, random);
        if (!MathUtilities.isNumberFirst(i)){
            loopForFirstNumber(b);
        }
        return i;
    }

    public static void pumpItemBattery(EntityPlayer player, DamageSource source, int damageAmount){
        if (player.worldObj.isRemote){ return; }

        ItemStack stack = player.inventory.getStackInSlot(8);
        if (stack != null && stack.getItem() == ItemList.personalShield) {
            if (stack.getItemDamage() < 256) {
                if (source.damageType.equalsIgnoreCase("lava")) {
                    stack.damageItem(damageAmount / loopForFirstNumber(true), player);
                } else {
                    stack.damageItem(damageAmount / loopForFirstNumber(false), player);
                }
            }
        }
    }

    public void rechargeItemBattery(ItemStack stack, int amount){
        if (stack != null && stack.getItem() == ItemList.personalShield) {
             stack.setItemDamage(stack.getItemDamage() - amount);
        }
    }

    public int getMaximumBatteryValue() {
        return 256;
    }

    public int getCurrentBatteryValue(ItemStack toCheck) {
        return 256 - toCheck.getItemDamage();
    }

    public boolean canRechargeItem(ItemStack toCheck) {
        return true;
    }
}
