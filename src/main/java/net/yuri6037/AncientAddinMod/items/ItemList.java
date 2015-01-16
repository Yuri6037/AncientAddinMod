package net.yuri6037.AncientAddinMod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.yuri6037.AncientAddinMod.AncientAddin;
import net.yuri6037.AncientAddinMod.enums.ArmorEnums;

public class ItemList {

    public static final Item droneLauncher;
    public static final Item droneRecharge;
    public static final Item ancientGlass;
    public static final Item personalShield;
    public static final Item rayonThrower;
    public static final Item battery;

    public static final Item heavyArmor_helmet;
    public static final Item heavyArmor_body;
    public static final Item heavyArmor_legs;
    public static final Item heavyArmor_boots;

    private static Item registerItem(String id, Item i) {
        GameRegistry.registerItem(i, "ancientAddin." + id);
        return i;
    }

    static {
        droneLauncher = registerItem("droneLauncher", new ItemHandDroneLauncher()).setTextureName("iron_ingot");
        droneRecharge = registerItem("droneRecharge", new ItemDroneRecharge()).setTextureName("iron_ingot");
        ancientGlass = registerItem("ancientGlasses", new ItemGlasses());
        personalShield = registerItem("personalShield", new ItemPersonalShield()).setCreativeTab(AncientAddin.ancientAddinTab);
        rayonThrower = registerItem("rayonThrower", new ItemHandRayonThrower()).setTextureName("iron_ingot");
        battery = registerItem("battery", new ItemIronBattery());

        heavyArmor_helmet = registerItem("heavyArmor0", new ItemHeavyArmor(ArmorEnums.HEAVY_ARMOR, 0)).setUnlocalizedName("HeavyArmor_0");
        heavyArmor_body = registerItem("heavyArmor1", new ItemHeavyArmor(ArmorEnums.HEAVY_ARMOR, 1)).setUnlocalizedName("HeavyArmor_1");
        heavyArmor_legs = registerItem("heavyArmor2", new ItemHeavyArmor(ArmorEnums.HEAVY_ARMOR, 2)).setUnlocalizedName("HeavyArmor_2");
        heavyArmor_boots = registerItem("heavyArmor3", new ItemHeavyArmor(ArmorEnums.HEAVY_ARMOR, 3)).setUnlocalizedName("HeavyArmor_3");
    }
}
