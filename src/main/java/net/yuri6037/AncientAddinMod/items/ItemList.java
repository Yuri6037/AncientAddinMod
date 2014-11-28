package net.yuri6037.AncientAddinMod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ItemList {

    public static final Item droneLauncher;

    private static Item registerItem(String id, Item i){
        GameRegistry.registerItem(i, "ancientAddin." + id);
        return i;
    }

    static {
        droneLauncher = registerItem("droneLauncher", new ItemHandDroneLauncher());
    }
}
