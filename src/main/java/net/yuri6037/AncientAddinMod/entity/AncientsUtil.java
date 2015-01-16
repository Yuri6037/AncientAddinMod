package net.yuri6037.AncientAddinMod.entity;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.yuri6037.AncientAddinMod.packet.PacketManager;
import net.yuri6037.AncientAddinMod.packet.data.PacketExtendedPlayer;

import java.util.List;

public abstract class AncientsUtil {

    public static boolean isPlayerAncient(EntityPlayer ply) {
        ExtendedPlayer player = (ExtendedPlayer) ply.getExtendedProperties(ExtendedPlayer.extensionName);
        return player != null && player.isAncient;
    }

    public static void setPlayerExtension(EntityPlayer player){
        ExtendedPlayer ply = new ExtendedPlayer();
        player.registerExtendedProperties(ExtendedPlayer.extensionName, ply);
    }

    public static boolean needRegistrance(EntityPlayer player){
        return (player.getExtendedProperties(ExtendedPlayer.extensionName) == null || player.getExtendedProperties(ExtendedPlayer.extensionName) != null && !(player.getExtendedProperties(ExtendedPlayer.extensionName) instanceof ExtendedPlayer));
    }

    public static void setPlayerAncient(EntityPlayer ply){
        ExtendedPlayer player = (ExtendedPlayer) ply.getExtendedProperties(ExtendedPlayer.extensionName);
        if (player != null){
            player.isAncient = true;
        }
    }

    public static void removeAncientStatusFromPlayer(EntityPlayer ply){
        ExtendedPlayer player = (ExtendedPlayer) ply.getExtendedProperties(ExtendedPlayer.extensionName);
        if (player != null){
            player.isAncient = false;
        }
    }

    public static class CommandDefineAncient implements ICommand {
        public String getCommandName() {
            return "setAncient";
        }

        public String getCommandUsage(ICommandSender p_71518_1_) {
            return "/setAncient <PlayerName>";
        }

        public List getCommandAliases() {
            return null;
        }

        public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
            if (p_71515_2_.length < 1) {
                return;
            }

            EntityPlayer player = CommandBase.getPlayer(p_71515_1_, p_71515_2_[0]);
            AncientsUtil.setPlayerAncient(player);
            PacketManager.sendPacketToClient(new PacketExtendedPlayer(player), player);
        }

        public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
            if (p_71519_1_ instanceof EntityPlayerMP){
                EntityPlayer ply = (EntityPlayer) p_71519_1_;
                return ((EntityPlayerMP) p_71519_1_).mcServer.getConfigurationManager().func_152596_g(ply.getGameProfile());
            }
            return false;
        }

        public int compareTo(Object o) {
            return 0;
        }

        /**
         * Adds the strings available in this command to the given list of tab completion options.
         */
        public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
        {
            return p_71516_2_.length == 1 ? CommandBase.getListOfStringsMatchingLastWord(p_71516_2_, this.getPlayers()) : null;
        }

        protected String[] getPlayers()
        {
            return MinecraftServer.getServer().getAllUsernames();
        }

        /**
         * Return whether the specified command parameter index is a username parameter.
         */
        public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
        {
            return p_82358_2_ == 0;
        }
    }

    public static class CommandUnDefineAncient implements ICommand {
        public String getCommandName() {
            return "unsetAncient";
        }

        public String getCommandUsage(ICommandSender p_71518_1_) {
            return "/unsetAncient <PlayerName>";
        }

        public List getCommandAliases() {
            return null;
        }

        public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
            if (p_71515_2_.length < 1) {
                return;
            }

            EntityPlayer player = CommandBase.getPlayer(p_71515_1_, p_71515_2_[0]);
            AncientsUtil.removeAncientStatusFromPlayer(player);
            PacketManager.sendPacketToClient(new PacketExtendedPlayer(player), player);
        }

        public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
            if (p_71519_1_ instanceof EntityPlayerMP){
                EntityPlayer ply = (EntityPlayer) p_71519_1_;
                return ((EntityPlayerMP) p_71519_1_).mcServer.getConfigurationManager().func_152596_g(ply.getGameProfile());
            }
            return false;
        }

        public int compareTo(Object o) {
            return 0;
        }

        /**
         * Adds the strings available in this command to the given list of tab completion options.
         */
        public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
        {
            return p_71516_2_.length == 1 ? CommandBase.getListOfStringsMatchingLastWord(p_71516_2_, this.getPlayers()) : null;
        }

        protected String[] getPlayers()
        {
            return MinecraftServer.getServer().getAllUsernames();
        }

        /**
         * Return whether the specified command parameter index is a username parameter.
         */
        public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
        {
            return p_82358_2_ == 0;
        }
    }
}
