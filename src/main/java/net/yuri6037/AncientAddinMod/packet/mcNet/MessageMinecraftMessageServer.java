package net.yuri6037.AncientAddinMod.packet.mcNet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.yuri6037.AncientAddinMod.packet.data.Packet;
import net.yuri6037.AncientAddinMod.packet.data.PacketList;
import org.apache.logging.log4j.LogManager;

public class MessageMinecraftMessageServer implements IMessage {

    protected Packet thePacket;

    public MessageMinecraftMessageServer(){}
    public MessageMinecraftMessageServer(Packet packet){
        thePacket = packet;
    }

    public void fromBytes(ByteBuf buf) {
        int id = buf.readInt();
        try {
            Packet packet = PacketList.getPacketFromID(id, Side.CLIENT);
            packet.readData(buf);
            thePacket = packet;
        } catch (IllegalAccessException e) {
            LogManager.getLogger().warn("Unable to read Ancient packet : IllegalAccessException !");
        } catch (InstantiationException e) {
            LogManager.getLogger().warn("Unable to read Ancient packet : InstantiationException !");
        }
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(thePacket.getPacketID());
        thePacket.writeData(buf);
    }
}
