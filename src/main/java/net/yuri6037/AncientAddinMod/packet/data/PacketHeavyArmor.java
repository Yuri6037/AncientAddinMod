package net.yuri6037.AncientAddinMod.packet.data;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.yuri6037.AncientAddinMod.enums.EnumHeavyArmorActions;
import net.yuri6037.AncientAddinMod.enums.EnumHeavyArmorFeature;
import net.yuri6037.AncientAddinMod.items.ItemHeavyArmor;

public class PacketHeavyArmor implements Packet {

    private EnumHeavyArmorActions action;

    public PacketHeavyArmor(){}

    public PacketHeavyArmor(EnumHeavyArmorActions act){
        action = act;
    }

    private int getIDFromAction(EnumHeavyArmorActions action){
        switch (action){
            case TOTALSCREEN_OPEN:
                return 0;
            case TOTALSCREEN_CLOSE:
                return 1;
            case PRESURIZE:
                return 2;
            case UNPRESURIZE:
                return 3;
            case LIGHTSYSTEM_ON:
                return 4;
            case LIGHTSYSTEM_OFF:
                return 5;
            case JET_USE:
                return 6;
            default:
                return -1;
        }
    }

    private EnumHeavyArmorActions getActionFromID(int id){
        switch (id){
            case 0:
                return EnumHeavyArmorActions.TOTALSCREEN_OPEN;
            case 1:
                return EnumHeavyArmorActions.TOTALSCREEN_CLOSE;
            case 2:
                return EnumHeavyArmorActions.PRESURIZE;
            case 3:
                return EnumHeavyArmorActions.UNPRESURIZE;
            case 4:
                return EnumHeavyArmorActions.LIGHTSYSTEM_ON;
            case 5:
                return EnumHeavyArmorActions.LIGHTSYSTEM_OFF;
            case 6:
                return EnumHeavyArmorActions.JET_USE;
            default:
                return null;
        }
    }

    public void writeData(ByteBuf buf) {
        if (action == null){
            buf.writeInt(-1);
            throw new NullPointerException("HEAVYARMOR_NET_ERROR : No action to perform !");
        }
        buf.writeInt(getIDFromAction(action));
    }

    public void readData(ByteBuf buf) {
        int id = buf.readInt();
        if (id == -1){
            throw new NullPointerException("HEAVYARMOR_NET_ERROR : Unreconized unique identifier !");
        }
        action = getActionFromID(id);
    }

    public void handleData(MessageContext ctx) {
        if (action != null) {
            EntityPlayer ply = ctx.getServerHandler().playerEntity;
            switch (action) {
                case TOTALSCREEN_OPEN:
                    ItemHeavyArmor.armorSwitchFeature(ply, EnumHeavyArmorFeature.TOTALSCREEN, false);
                    break;
                case TOTALSCREEN_CLOSE:
                    ItemHeavyArmor.armorSwitchFeature(ply, EnumHeavyArmorFeature.TOTALSCREEN, true);
                    break;
                case PRESURIZE:
                    ItemHeavyArmor.armorSwitchFeature(ply, EnumHeavyArmorFeature.PRESURIZATION, true);
                    break;
                case UNPRESURIZE:
                    ItemHeavyArmor.armorSwitchFeature(ply, EnumHeavyArmorFeature.PRESURIZATION, false);
                    break;
                case LIGHTSYSTEM_ON:
                    ItemHeavyArmor.armorSwitchFeature(ply, EnumHeavyArmorFeature.NIGHT_VISION, true);
                    break;
                case LIGHTSYSTEM_OFF:
                    ItemHeavyArmor.armorSwitchFeature(ply, EnumHeavyArmorFeature.NIGHT_VISION, false);
                    break;
                case JET_USE:
                    if (ItemHeavyArmor.canUseArmor(ply)) {
                        ctx.getServerHandler().playerEntity.motionY += 0.1D;
                        ctx.getServerHandler().playerEntity.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, 5));
                        ItemHeavyArmor.pumpItemBattery(ply, EnumHeavyArmorFeature.JET_USE);
                    }
                    break;
            }
        }
    }

    public int getPacketID() {
        return 0;
    }
}
