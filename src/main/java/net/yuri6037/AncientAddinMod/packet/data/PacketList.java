package net.yuri6037.AncientAddinMod.packet.data;

import cpw.mods.fml.relauncher.Side;

import java.util.HashMap;
import java.util.Map;

public class PacketList {

    public static final Map<Integer, Class<? extends Packet>> classMapClient;
    public static final Map<Integer, Class<? extends Packet>> classMapServer;

    public static void registerPacket(Class<? extends Packet> packet, Side sendSide){
        if (sendSide == Side.CLIENT){
            try {
                classMapClient.put(packet.newInstance().getPacketID(), packet);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                classMapServer.put(packet.newInstance().getPacketID(), packet);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static Packet getPacketFromID(int pckID, Side receiveSide) throws IllegalAccessException, InstantiationException {
        if (receiveSide == Side.CLIENT) {
            Class<? extends Packet> cl = classMapServer.get(pckID);
            if (cl == null) {
                return null;
            }
            return cl.newInstance();
        } else {
            Class<? extends Packet> cl = classMapClient.get(pckID);
            if (cl == null) {
                return null;
            }
            return cl.newInstance();
        }
    }

    static {
        classMapClient = new HashMap<Integer, Class<? extends Packet>>();
        classMapServer = new HashMap<Integer, Class<? extends Packet>>();
        registerPacket(PacketHeavyArmor.class, Side.CLIENT);
        registerPacket(PacketExtendedPlayer.class, Side.SERVER);
    }
}
