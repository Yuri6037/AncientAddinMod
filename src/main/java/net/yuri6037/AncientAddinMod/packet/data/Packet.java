package net.yuri6037.AncientAddinMod.packet.data;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public interface Packet {

    public void writeData(ByteBuf buf);
    public void readData(ByteBuf buf);
    public void handleData(MessageContext ctx);
    public int getPacketID();

}
