package net.yuri6037.AncientAddinMod.blocks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.yuri6037.AncientAddinMod.items.IRechargableBattery;
import net.yuri6037.AncientAddinMod.items.ItemBatteryBase;

public class TileEntityBatteryRecharger extends TileEntity implements IInventory {

    private ItemStack[] inv;

    private boolean isRechargingItem;

    private int ticks;
    private int angleDeg;

    public TileEntityBatteryRecharger(){
        inv = new ItemStack[2];
        isRechargingItem = false;
    }

    public int getSizeInventory() {
        return 2;
    }

    public ItemStack getStackInSlot(int id) {
        return inv[id];
    }

    public ItemStack decrStackSize(int id, int amount) {
        ItemStack stack = getStackInSlot(id);
        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(id, null);
            } else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(id, null);
                }
            }
        }
        return stack;
    }

    public ItemStack getStackInSlotOnClosing(int id) {
        return inv[id];
    }

    public void setInventorySlotContents(int id, ItemStack stack) {
        inv[id] = stack;
    }

    public String getInventoryName() {
        return "BatteryRecharger";
    }

    public boolean hasCustomInventoryName() {
        return true;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int id, ItemStack stack) {
        return false;
    }

    /**
     * Do recharge stuff
     */
    public void updateEntity() {
        ItemStack battery = getStackInSlot(0);
        ItemStack itemToRecharge = getStackInSlot(1);
        if (battery != null && itemToRecharge != null){
            ItemBatteryBase base = (ItemBatteryBase) battery.getItem();
            IRechargableBattery rechargableBattery = (IRechargableBattery) itemToRecharge.getItem();
            if (!rechargableBattery.canRechargeItem(itemToRecharge)){
                return;
            }
            int cur = rechargableBattery.getCurrentBatteryValue(itemToRecharge);
            int max = rechargableBattery.getMaximumBatteryValue();
            if (base.canPump(battery, 1) && cur < max){
                setInventorySlotContents(0, base.pump(battery, 1));
                rechargableBattery.rechargeItemBattery(itemToRecharge, 1);
                isRechargingItem = true;
                doRechargingAnimation();
                return;
            }
        }

        isRechargingItem = false;
    }

    public boolean isRechargingItem(){
        return isRechargingItem;
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList nbttaglist = tag.getTagList("Items", 10);
        inv = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < inv.length)
            {
                inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < inv.length; ++i)
        {
            if (inv[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                inv[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tag.setTag("Items", nbttaglist);
    }

    private void doRechargingAnimation(){
        ticks++;
        if (ticks >= 1) {
            angleDeg += 5;
            ticks = 0;
            if (angleDeg >= 360) {
                angleDeg = 0;
            }
        }
    }

    public float getRechargingRotationAngle(){
        return angleDeg;
    }
}
