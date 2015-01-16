package net.yuri6037.AncientAddinMod.packet.mcNet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class HandlerMinecraftMessageServer implements IMessageHandler<MessageMinecraftMessageServer, IMessage> {

    public IMessage onMessage(MessageMinecraftMessageServer message, MessageContext ctx) {
        if (message.thePacket != null){
            message.thePacket.handleData(ctx);
        }
        return null;
    }

}
