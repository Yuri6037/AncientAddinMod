package net.yuri6037.AncientAddinMod.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.yuri6037.AncientAddinMod.packet.data.Packet;
import net.yuri6037.AncientAddinMod.packet.mcNet.HandlerMinecraftMessageClient;
import net.yuri6037.AncientAddinMod.packet.mcNet.HandlerMinecraftMessageServer;
import net.yuri6037.AncientAddinMod.packet.mcNet.MessageMinecraftMessageClient;
import net.yuri6037.AncientAddinMod.packet.mcNet.MessageMinecraftMessageServer;

public class PacketManager {
    private static SimpleNetworkWrapper networkSystem;

    @SideOnly(Side.CLIENT)
    public static void sendPacketToServer(Packet packet){
        networkSystem.sendToServer(new MessageMinecraftMessageClient(packet));
    }

    public static void sendPacketToClient(Packet packet, EntityPlayer to){
        networkSystem.sendTo(new MessageMinecraftMessageServer(packet), (net.minecraft.entity.player.EntityPlayerMP) to);
    }

    static {
        networkSystem = NetworkRegistry.INSTANCE.newSimpleChannel("AncientAddinMod");
        networkSystem.registerMessage(HandlerMinecraftMessageClient.class, MessageMinecraftMessageClient.class, 0, Side.SERVER);
        networkSystem.registerMessage(HandlerMinecraftMessageServer.class, MessageMinecraftMessageServer.class, 1, Side.CLIENT);
    }
}
