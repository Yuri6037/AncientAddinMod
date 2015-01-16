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
import net.yuri6037.AncientAddinMod.entity.EntityDrone;

/**
 * Made by Yuri6037
 */
public class ItemHandDroneLauncher extends Item {
	
    public ItemHandDroneLauncher() {
		setMaxDamage(129);
        setCreativeTab(AncientAddin.ancientAddinTab);
        setUnlocalizedName("droneLauncher");
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
    		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ItemList.droneLauncher && tag.getBoolean("firstShoot") && tag.getInteger("shootTime") <= 0){
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
        if (!world.isRemote && !tag.getBoolean("firstShoot") && batery == 0 && entity.inventory.hasItem(ItemList.droneRecharge)) {
            tag.setBoolean("firstShoot", true);
            tag.setInteger("shootTime", 128);
            itemstack.damageItem(128, entity);
            tag.setInteger("Battery", 2);
            entity.inventory.consumeInventoryItem(ItemList.droneRecharge);
            return itemstack;
        }
		if (!world.isRemote && !tag.getBoolean("firstShoot") && batery > 0) {
			Vec3 look = entity.getLookVec();
			EntityDrone drone = new EntityDrone(world, entity, 1, 1, 1);
            drone.setPosition(entity.posX + look.xCoord, entity.posY + 1, entity.posZ + look.zCoord);
            drone.accelerationX = look.xCoord * 0.01;
            drone.accelerationY = look.yCoord * 0.01;
            drone.accelerationZ = look.zCoord * 0.01;
			world.spawnEntityInWorld(drone);
            if (batery > 1) {
                tag.setBoolean("firstShoot", true);
                tag.setInteger("shootTime", 64);
                itemstack.damageItem(64, entity);
            }
            tag.setInteger("Battery", batery - 1);
		}		
		return itemstack;
	}
}
