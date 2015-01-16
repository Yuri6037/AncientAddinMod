package net.yuri6037.AncientAddinMod.items;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.yuri6037.AncientAddinMod.blocks.BlockList;

public class CraftingList {

    private static void addRecipe(ItemStack stack, Object[] obj){
        CraftingManager.getInstance().addRecipe(stack, obj);
    }

    private static void addSmeting(ItemStack stack, ItemStack stack1){
        FurnaceRecipes.smelting().func_151394_a(stack, stack1, 0.0F);
    }

    static {
        //Heavy Armor recipes
        addRecipe(new ItemStack(ItemList.heavyArmor_helmet), new Object[]{"XXX", "###", "XXX", 'X', Blocks.iron_block, '#', Items.diamond});
        addRecipe(new ItemStack(ItemList.heavyArmor_body), new Object[]{"X#X", "###", "X#X", 'X', Blocks.iron_block, '#', Blocks.diamond_block});
        addRecipe(new ItemStack(ItemList.heavyArmor_legs), new Object[]{"###", "X X", "X X", 'X', Blocks.iron_block, '#', Blocks.diamond_block});
        addRecipe(new ItemStack(ItemList.heavyArmor_boots), new Object[]{"   ", "X X", "X X", 'X', BlockList.blackDiamond});
        //The personal shield recipe
        addRecipe(new ItemStack(ItemList.personalShield), new Object[]{"xxx", "xcx", "xxx", 'x', Items.iron_ingot, 'c', Items.diamond});
        //Weapons recipes
        addRecipe(new ItemStack(ItemList.droneLauncher), new Object[]{"xxc", "www", "xxc", 'x', Items.iron_ingot, 'c', Items.diamond, 'w', Items.gold_ingot});
        addRecipe(new ItemStack(ItemList.rayonThrower), new Object[]{"www", "  x", 'w', Items.gold_ingot, 'x', Items.iron_ingot});
        //Weapons recharges recipes
        addRecipe(new ItemStack(ItemList.droneRecharge, 1), new Object[]{"XXX", "X#X", "XXX", 'X', BlockList.blackDiamond, '#', Blocks.gold_block});
        addRecipe(new ItemStack(ItemList.battery, 2), new Object[]{" X ", "X#X", "X#X", 'X', BlockList.blackDiamond, '#', Blocks.gold_block});
        //The Glasses recipe
        addRecipe(new ItemStack(ItemList.ancientGlass), new Object[]{"cxc", "www", "cxc", 'c', Items.iron_ingot, 'x', Items.gold_ingot, 'w', Items.diamond});
        //The ItemDisassembler recipe
        addRecipe(new ItemStack(BlockList.itemDissasembler), new Object[]{" W ", "X#X", " C ", 'X', Items.blaze_rod, 'W', Items.blaze_powder, 'C', Items.brewing_stand, '#', Blocks.crafting_table});
        //The staze module core recipe
        addRecipe(new ItemStack(BlockList.stazeModuleCore), new Object[]{"X#X", "XXX", 'X', Blocks.iron_block, '#', Blocks.diamond_block});
        //The battery rehcarger block recipe
        addRecipe(new ItemStack(BlockList.batteryRecharger), new Object[]{" D ", " # ", "X#X", 'D', Items.diamond, '#', BlockList.blackDiamond, 'X', Blocks.iron_block});

        //The black diamond recipe
        addSmeting(new ItemStack(Blocks.diamond_block), new ItemStack(BlockList.blackDiamond));
    }
}
