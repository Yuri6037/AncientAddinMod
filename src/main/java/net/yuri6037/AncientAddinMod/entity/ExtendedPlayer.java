package net.yuri6037.AncientAddinMod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.yuri6037.AncientAddinMod.packet.PacketManager;
import net.yuri6037.AncientAddinMod.packet.data.PacketExtendedPlayer;

public class ExtendedPlayer implements IExtendedEntityProperties{

    private EntityPlayer thePlayer;

    public static final String extensionName = "AncientAddinExtendedPlayer";

    protected boolean isAncient;

    public void saveNBTData(NBTTagCompound compound) {
        compound.setBoolean("IsAncient", isAncient);
    }

    public void loadNBTData(NBTTagCompound compound) {
        isAncient = compound.getBoolean("IsAncient");
    }

    public void init(Entity entity, World world) {
        if (entity instanceof EntityPlayer){
            thePlayer = (EntityPlayer) entity;
        }
    }

    public void sendPacketUpdate(){
        if (!thePlayer.worldObj.isRemote) {
            PacketManager.sendPacketToClient(new PacketExtendedPlayer(thePlayer), thePlayer);
        }
    }
}
