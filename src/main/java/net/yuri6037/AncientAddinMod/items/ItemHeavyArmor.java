package net.yuri6037.AncientAddinMod.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.yuri6037.AncientAddinMod.AncientAddin;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.enums.ArmorEnums;
import net.yuri6037.AncientAddinMod.enums.EnumHeavyArmorFeature;

public class ItemHeavyArmor extends ItemAncientArmor implements IRechargableBattery {

    public ItemHeavyArmor(ArmorEnums material, int type) {
        super(material, type);
        setCreativeTab(AncientAddin.ancientAddinTab);
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (canPlayerGetEffects(player)) {
            if (armorHasFeatureEnabled(player, EnumHeavyArmorFeature.TOTALSCREEN) && pumpItemBattery(player, EnumHeavyArmorFeature.TOTALSCREEN)) {
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 60, 2, false));

                if (armorHasFeatureEnabled(player, EnumHeavyArmorFeature.NIGHT_VISION)) {
                    if (pumpItemBattery(player, EnumHeavyArmorFeature.NIGHT_VISION)) {
                        player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 60, 10, false));
                    }
                }

                if (armorHasFeatureEnabled(player, EnumHeavyArmorFeature.PRESURIZATION)) {
                    if (pumpItemBattery(player, EnumHeavyArmorFeature.PRESURIZATION)) {
                        player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 60, 20, false));
                    }
                }

                //Spawning jet particles
                if (!player.onGround) {
                    double x = (player.posX + player.getLookVec().xCoord);
                    double y = player.posY - 1.5;
                    double z = (player.posZ + player.getLookVec().zCoord);
                    double x1 = player.motionX;
                    double z1 = player.motionZ;
                    for (int i = 0; i < 10; i++) {
                        world.spawnParticle("smoke", x, y, z, x1, -0.1D, z1);
                    }
                }
            }
        }
    }

    public static boolean armorHasFeatureEnabled(EntityPlayer player, EnumHeavyArmorFeature feature) {
        if (!canPlayerGetEffects(player)) {
            return false;
        }

        ItemStack stack = player.inventory.getStackInSlot(39);
        if (stack.stackTagCompound != null) {
            switch (feature) {
                case PRESURIZATION:
                    return stack.stackTagCompound.getBoolean("Presurized");
                case NIGHT_VISION:
                    return stack.stackTagCompound.getBoolean("LightsEnabled");
                case TOTALSCREEN:
                    return stack.stackTagCompound.getBoolean("TotalScreen");
            }
        }
        return false;
    }

    public static boolean canUseArmor(EntityPlayer player){
        ItemStack stack = player.inventory.getStackInSlot(38);
        if (stack == null || stack.stackTagCompound == null){
            return false;
        }
        int bat = stack.stackTagCompound.getInteger("Battery");
        if (bat > 0) {
            return true;
        }
        return false;
    }

    public static boolean pumpItemBattery(EntityPlayer player, EnumHeavyArmorFeature feature){
        if (canPlayerGetEffects(player)) {
            ItemStack stack = player.inventory.getStackInSlot(38);
            if (stack == null){
                return false;
            }
            if (stack.stackTagCompound == null){
                player.inventory.getStackInSlot(38).stackTagCompound = new NBTTagCompound();
                player.inventory.getStackInSlot(38).stackTagCompound.setInteger("Battery", 512);
            }
            stack.stackTagCompound.setInteger("ticks", stack.stackTagCompound.getInteger("ticks") + 1);
            int bat = stack.stackTagCompound.getInteger("Battery");
            if (stack.stackTagCompound.getInteger("ticks") >= 70) {
                if (bat > 0) {
                    switch (feature) {
                        case PRESURIZATION:
                            bat -= 2;
                            break;
                        case NIGHT_VISION:
                            bat -= 2;
                            break;
                        case TOTALSCREEN:
                            bat -= 1;
                            break;
                        case JET_USE:
                            bat -= 15;
                            break;
                    }
                    if (bat < 0) {
                        bat = 0;
                    }
                    stack.stackTagCompound.setInteger("Battery", bat);
                }
                stack.stackTagCompound.setInteger("ticks", 0);
            }
            if (bat == 0){
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /*public static void rechargeItemBattery(EntityPlayer player){
        if (player.worldObj.isRemote){ return; }

        ItemStack stack = player.inventory.getStackInSlot(8);
        if (stack != null && stack.getItem() == ItemList.personalShield) {
            if (stack.getItemDamage() < 256) {
                stack.setItemDamage(stack.getItemDamage() + 1);
            }
        }
    }*/


    public static boolean armorSwitchFeature(EntityPlayer player, EnumHeavyArmorFeature feature, boolean b){
        if (!canPlayerGetEffects(player)) {
            return false;
        }

        ItemStack stack = player.inventory.getStackInSlot(39);
        if (stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
        }
        switch (feature) {
            case PRESURIZATION:
                stack.stackTagCompound.setBoolean("Presurized", b);
                return true;
            case NIGHT_VISION:
                stack.stackTagCompound.setBoolean("LightsEnabled", b);
                return true;
            case TOTALSCREEN:
                stack.stackTagCompound.setBoolean("TotalScreen", b);
                return true;
        }
        return false;
    }

    public static boolean canPlayerGetEffects(EntityPlayer player) {
        if (player == null || player.inventory == null){
            return false;
        }

        if (!AncientsUtil.isPlayerAncient(player)){
            return false;
        }

        ItemStack stack = player.inventory.getStackInSlot(39);
        ItemStack stack1 = player.inventory.getStackInSlot(38);
        ItemStack stack2 = player.inventory.getStackInSlot(37);
        ItemStack stack3 = player.inventory.getStackInSlot(36);

        if (stack != null && stack1 != null && stack2 != null && stack3 != null) {
            if (stack.getItem() == ItemList.heavyArmor_helmet && stack1.getItem() == ItemList.heavyArmor_body && stack2.getItem() == ItemList.heavyArmor_legs && stack3.getItem() == ItemList.heavyArmor_boots) {
                return true;
            }
        }
        return false;
    }

    public static void damageArmor(EntityPlayer player, int dmgAmount){
        player.inventory.getStackInSlot(39).damageItem(dmgAmount * 8, player);
        player.inventory.getStackInSlot(38).damageItem(dmgAmount * 2, player);
        player.inventory.getStackInSlot(37).damageItem(dmgAmount * 6, player);
        player.inventory.getStackInSlot(36).damageItem(dmgAmount * 10, player);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (armorType == 0 || armorType == 1 || armorType == 3) {
            if (entity instanceof EntityPlayer) {
                if (stack.stackTagCompound != null && !stack.stackTagCompound.getBoolean("TotalScreen")){
                    return "AncientAddinMod:textures/armors/HeavyArmor_0_1.png";
                }
            }
        }
        return super.getArmorTexture(stack, entity, slot, type);
    }

    public void rechargeItemBattery(ItemStack toRecharge, int amount) {
        if (toRecharge.stackTagCompound == null){
            toRecharge.stackTagCompound = new NBTTagCompound();
            toRecharge.stackTagCompound.setInteger("Battery", 512);
            return;
        }
        toRecharge.stackTagCompound.setInteger("Battery", toRecharge.stackTagCompound.getInteger("Battery") + amount);
    }

    public int getMaximumBatteryValue() {
        return 512;
    }

    public int getCurrentBatteryValue(ItemStack toCheck) {
        return toCheck.stackTagCompound.getInteger("Battery");
    }

    public boolean canRechargeItem(ItemStack toCheck) {
        return toCheck.stackTagCompound != null && toCheck.stackTagCompound.hasKey("Battery");
    }
}
