package net.yuri6037.AncientAddinMod.blocks.inventory;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.List;

public class TileEntityItemDisassembler extends TileEntity implements IInventory, ISidedInventory {

    private ItemStack[] inventory;

    private int ticks;

    public TileEntityItemDisassembler() {
        inventory = new ItemStack[9];
    }

    public int getSizeInventory() {
        return 9;
    }

    public ItemStack getStackInSlot(int id) {
        return inventory[id];
    }

    public ItemStack decrStackSize(int id, int count) {
        ItemStack stack = getStackInSlot(id);
        if (stack != null) {
            if (stack.stackSize <= count) {
                setInventorySlotContents(id, null);
            } else {
                stack = stack.splitStack(count);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(id, null);
                }
            }
        }
        return stack;
    }

    public ItemStack getStackInSlotOnClosing(int id) {
        return inventory[id];
    }

    public void setInventorySlotContents(int id, ItemStack stack) {
        inventory[id] = stack;
    }

    public String getInventoryName() {
        return "ItemDisassembler";
    }

    public boolean hasCustomInventoryName() {
        return true;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList nbttaglist = tag.getTagList("Items", 10);
        inventory = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < inventory.length)
            {
                inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < inventory.length; ++i)
        {
            if (inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tag.setTag("Items", nbttaglist);
    }

    public void updateEntity() {
        if (!worldObj.isRemote && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            ticks++;
            if (ticks >= 15) {
                for (int i = 0; i < 9; i++) {
                    ItemStack stack = getStackInSlot(i);
                    if (stack != null) {
                        List l = CraftingManager.getInstance().getRecipeList();
                        for (Object obj : l) {
                            if (obj instanceof ShapedRecipes) {
                                ShapedRecipes recipes = (ShapedRecipes) obj;
                                if (areStacksCorresponding(recipes.getRecipeOutput(), stack)) {
                                    decrStackSize(i, recipes.getRecipeOutput().stackSize);
                                    for (Object obj1 : recipes.recipeItems) {
                                        if (obj1 instanceof ItemStack) {
                                            ItemStack stack1 = (ItemStack) obj1;
                                            ItemStack givingItem = new ItemStack(stack1.getItem(), 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 instanceof Item) {
                                            ItemStack givingItem = new ItemStack((Item) obj1, 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 instanceof Block) {
                                            ItemStack givingItem = new ItemStack((Block) obj1, 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 != null) {
                                            ItemStack item = pirateMinecraftForgeUnmodifiableArrayList(obj1.toString());
                                            if (item != null) {
                                                ItemStack givingItem = new ItemStack(item.getItem(), 1);
                                                EntityItem entity = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                                worldObj.spawnEntityInWorld(entity);
                                            }
                                        }
                                    }
                                    ticks = 0;
                                    return;
                                }
                            } else if (obj instanceof ShapelessRecipes) {
                                ShapelessRecipes recipes = (ShapelessRecipes) obj;
                                if (areStacksCorresponding(recipes.getRecipeOutput(), stack)) {
                                    decrStackSize(i, recipes.getRecipeOutput().stackSize);
                                    for (Object obj1 : recipes.recipeItems) {
                                        if (obj1 instanceof ItemStack) {
                                            ItemStack stack1 = (ItemStack) obj1;
                                            ItemStack givingItem = new ItemStack(stack1.getItem(), 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 instanceof Item) {
                                            ItemStack givingItem = new ItemStack((Item) obj1, 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 instanceof Block) {
                                            ItemStack givingItem = new ItemStack((Block) obj1, 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 != null) {
                                            ItemStack item = pirateMinecraftForgeUnmodifiableArrayList(obj1.toString());
                                            if (item != null) {
                                                ItemStack givingItem = new ItemStack(item.getItem(), 1);
                                                EntityItem entity = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                                worldObj.spawnEntityInWorld(entity);
                                            }
                                        }
                                    }
                                    ticks = 0;
                                    return;
                                }
                            } else if (obj instanceof ShapelessOreRecipe) {
                                ShapelessOreRecipe recipes = (ShapelessOreRecipe) obj;
                                if (areStacksCorresponding(recipes.getRecipeOutput(), stack)) {
                                    decrStackSize(i, recipes.getRecipeOutput().stackSize);
                                    for (Object obj1 : recipes.getInput()) {
                                        if (obj1 instanceof ItemStack) {
                                            ItemStack stack1 = (ItemStack) obj1;
                                            ItemStack givingItem = new ItemStack(stack1.getItem(), 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 instanceof Item) {
                                            ItemStack givingItem = new ItemStack((Item) obj1, 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 instanceof Block) {
                                            ItemStack givingItem = new ItemStack((Block) obj1, 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 != null) {
                                            ItemStack item = pirateMinecraftForgeUnmodifiableArrayList(obj1.toString());
                                            if (item != null) {
                                                ItemStack givingItem = new ItemStack(item.getItem(), 1);
                                                EntityItem entity = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                                worldObj.spawnEntityInWorld(entity);
                                            }
                                        }
                                    }
                                    ticks = 0;
                                    return;
                                }
                            } else if (obj instanceof ShapedOreRecipe) {
                                ShapedOreRecipe recipes = (ShapedOreRecipe) obj;
                                if (areStacksCorresponding(recipes.getRecipeOutput(), stack)) {
                                    decrStackSize(i, recipes.getRecipeOutput().stackSize);
                                    for (Object obj1 : recipes.getInput()) {
                                        if (obj1 instanceof ItemStack) {
                                            ItemStack stack1 = (ItemStack) obj1;
                                            ItemStack givingItem = new ItemStack(stack1.getItem(), 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 instanceof Item) {
                                            ItemStack givingItem = new ItemStack((Item) obj1, 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 instanceof Block) {
                                            ItemStack givingItem = new ItemStack((Block) obj1, 1);
                                            EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                            worldObj.spawnEntityInWorld(item);
                                        } else if (obj1 != null) {
                                            ItemStack item = pirateMinecraftForgeUnmodifiableArrayList(obj1.toString());
                                            if (item != null) {
                                                ItemStack givingItem = new ItemStack(item.getItem(), 1);
                                                EntityItem entity = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, givingItem);
                                                worldObj.spawnEntityInWorld(entity);
                                            }
                                        }
                                    }
                                    ticks = 0;
                                    return;
                                }
                            }
                        }
                    }
                    ticks = 0;
                }
            }
        }
    }

    private ItemStack pirateMinecraftForgeUnmodifiableArrayList(String s) {
        String s1 = s.split("\\[")[1];
        String s2 = s1.split("\\]")[0];
        String s3 = s2.split("x")[1];
        String s4 = s3.split("@")[0];

        if (s4.startsWith("tile.")) {
            for (Object i : Block.blockRegistry) {
                Block item = (Block) i;
                if (item.getUnlocalizedName().equalsIgnoreCase(s4)) {
                    return new ItemStack(item);
                }
            }
        } else if (s4.startsWith("item.")) {
            for (Object i : Item.itemRegistry) {
                Item item = (Item) i;
                if (item.getUnlocalizedName().equalsIgnoreCase(s4)) {
                    return new ItemStack(item);
                }
            }
        }
        return null;
    }

    private boolean areStacksCorresponding(ItemStack stack0, ItemStack stack1) {
        return stack0.getItem().getUnlocalizedName().equalsIgnoreCase(stack1.getItem().getUnlocalizedName()) && stack0.getItemDamage() == stack1.getItemDamage() && stack0.stackSize <= stack1.stackSize;
    }

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    }

    public boolean canInsertItem(int slot, ItemStack item, int side) {
        ItemStack stack = inventory[slot];
        return stack == null || stack.getItem() == item.getItem() && stack.getItemDamage() == item.getItemDamage() && (stack.stackSize + item.stackSize) < 65;
    }

    public boolean canExtractItem(int slot, ItemStack item, int side) {
        ItemStack stack = inventory[slot];
        return stack == null || stack.getItem() == item.getItem() && stack.getItemDamage() == item.getItemDamage() && (stack.stackSize + item.stackSize) < 65;
    }
}
