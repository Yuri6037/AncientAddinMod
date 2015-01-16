package net.yuri6037.AncientAddinMod.packet.data;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;

public class PacketExtendedPlayer implements Packet {

    private EntityPlayer thePlayer;

    private boolean readBool;

    public PacketExtendedPlayer(EntityPlayer player){
        thePlayer = player;
    }

    public PacketExtendedPlayer(){}

    public void writeData(ByteBuf buf) {
        buf.writeBoolean(AncientsUtil.isPlayerAncient(thePlayer));
    }

    public void readData(ByteBuf buf) {
        readBool = buf.readBoolean();
    }

    @SideOnly(Side.CLIENT)
    public void handleData(MessageContext ctx) {
        if (readBool) {
            AncientsUtil.setPlayerAncient(Minecraft.getMinecraft().thePlayer);
        } else {
            AncientsUtil.removeAncientStatusFromPlayer(Minecraft.getMinecraft().thePlayer);
        }
    }

    public int getPacketID() {
        return 1;
    }
}
