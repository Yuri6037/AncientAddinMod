package net.yuri6037.AncientAddinMod.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.yuri6037.AncientAddinMod.AncientAddin;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.entity.EntityAncientRayon;

public class ItemHandRayonThrower extends Item{

    public ItemHandRayonThrower() {
        setMaxDamage(32);
        setCreativeTab(AncientAddin.ancientAddinTab);
        setUnlocalizedName("rayonThrower");
        setMaxStackSize(1);
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par1ItemStack.stackTagCompound == null){
            return;
        }
        NBTTagCompound tag = par1ItemStack.stackTagCompound;
        if (par3Entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)par3Entity;
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ItemList.rayonThrower && tag.getBoolean("firstShoot") && tag.getInteger("shootTime") <= 0){
                tag.setBoolean("firstShoot", false);
            }
            if (tag.getInteger("shootTime") > 0 && tag.getBoolean("firstShoot")){
                tag.setInteger("shootTime", tag.getInteger("shootTime") - 1);
                par1ItemStack.setItemDamage(tag.getInteger("shootTime"));
                if (tag.getInteger("shootTime") <= 1)
                    par1ItemStack.setItemDamage(0);
            }
        }
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world,	EntityPlayer entity) {
        if (!AncientsUtil.isPlayerAncient(entity)){
            return itemstack;
        }

        if (itemstack.stackTagCompound == null){
            itemstack.stackTagCompound = new NBTTagCompound();
            itemstack.stackTagCompound.setInteger("Battery", 0);
        }
        NBTTagCompound tag = itemstack.stackTagCompound;
        int batery = tag.getInteger("Battery");
        if (!world.isRemote && !tag.getBoolean("firstShoot") && batery == 0 && entity.inventory.hasItem(ItemList.battery)) {
            tag.setBoolean("firstShoot", true);
            tag.setInteger("shootTime", 32);
            itemstack.damageItem(32, entity);
            tag.setInteger("Battery", 16);
            entity.inventory.consumeInventoryItem(ItemList.battery);
            return itemstack;
        }
        if (!world.isRemote && !tag.getBoolean("firstShoot") && batery > 0) {
            Vec3 look = entity.getLookVec();
            EntityAncientRayon rayon = new EntityAncientRayon(world, entity, 1, 1, 1);
            rayon.setPosition(entity.posX + look.xCoord, entity.posY + 1, entity.posZ + look.zCoord);
            rayon.accelerationX = look.xCoord * 1;
            rayon.accelerationY = look.yCoord * 1;
            rayon.accelerationZ = look.zCoord * 1;
            world.spawnEntityInWorld(rayon);
            if (batery > 1) {
                tag.setBoolean("firstShoot", true);
                tag.setInteger("shootTime", 32);
                itemstack.damageItem(32, entity);
            }
            tag.setInteger("Battery", batery - 1);
        }
        return itemstack;
    }
}
