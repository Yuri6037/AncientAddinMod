package net.yuri6037.AncientAddinMod.packet.mcNet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class HandlerMinecraftMessageClient implements IMessageHandler<MessageMinecraftMessageClient, IMessage> {

    public IMessage onMessage(MessageMinecraftMessageClient message, MessageContext ctx) {
        if (message.thePacket != null){
            message.thePacket.handleData(ctx);
        }
        return null;
    }

}
