package net.yuri6037.AncientAddinMod.items;

public class ItemIronBattery extends ItemBatteryBase {

    public ItemIronBattery(){
        super();
    }

    public int getMaxJuiceValue() {
        return 256;
    }

    public String getBatteryTexture() {
        return "iron_ingot";
    }

    public String getBatteryName() {
        return "battery";
    }
}
