package net.yuri6037.AncientAddinMod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.yuri6037.AncientAddinMod.blocks.BlockList;
import net.yuri6037.AncientAddinMod.blocks.render.BlockRenderList;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.entity.EntityList;
import net.yuri6037.AncientAddinMod.entity.render.RenderList;
import net.yuri6037.AncientAddinMod.event.ClientHandler;
import net.yuri6037.AncientAddinMod.event.KeyRegistry;
import net.yuri6037.AncientAddinMod.event.ServerHandler;
import net.yuri6037.AncientAddinMod.items.CraftingList;
import net.yuri6037.AncientAddinMod.items.ItemList;
import net.yuri6037.AncientAddinMod.items.render.ItemRenderList;
import net.yuri6037.AncientAddinMod.packet.PacketManager;
import org.apache.logging.log4j.LogManager;

@Mod(modid = "AncientAddin", name = "AncientAddin Mod", version = AncientAddin.MOD_VERCION)
public class AncientAddin {
    public static CreativeTabs ancientAddinTab;

    public static final String MOD_VERCION = "Alpha #1";

    @Mod.Instance
    public static AncientAddin ancientAddin;

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event){
        event.registerServerCommand(new AncientsUtil.CommandDefineAncient());
        event.registerServerCommand(new AncientsUtil.CommandUnDefineAncient());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LogManager.getLogger().info("--> AncientAddin Initializing... <--");

        LogManager.getLogger().info("Adding CreativeTab");
        ancientAddinTab = new CreativeTabs("ancient") {
            @SideOnly(Side.CLIENT)
            public ItemStack getIconItemStack() {
                return new ItemStack(BlockList.blackDiamond);
            }

            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return null;
            }
        };
        LogManager.getLogger().info("Adding Blocks...");
        new BlockList();
        LogManager.getLogger().info("Adding Items...");
        new ItemList();
        LogManager.getLogger().info("Adding Crafts...");
        new CraftingList();
        LogManager.getLogger().info("Adding Entities...");
        new EntityList();
        LogManager.getLogger().info("Adding ServerHandler...");
        ServerHandler handler = new ServerHandler();
        FMLCommonHandler.instance().bus().register(handler);
        MinecraftForge.EVENT_BUS.register(handler);
        LogManager.getLogger().info("Adding Network Systems...");
        new PacketManager();
        NetworkRegistry.INSTANCE.registerGuiHandler(ancientAddin, new GuiHandler());

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
            LogManager.getLogger().info("Adding Entities Renderers...");
            new RenderList();
            LogManager.getLogger().info("Adding Blocks Renderers...");
            new BlockRenderList();
            LogManager.getLogger().info("Adding Items Renderers...");
            new ItemRenderList();
            LogManager.getLogger().info("Adding ClientHandler...");
            ClientHandler handler1 = new ClientHandler();
            FMLCommonHandler.instance().bus().register(handler1);
            MinecraftForge.EVENT_BUS.register(handler1);
            new KeyRegistry();
        }
        LogManager.getLogger().info("--> Initialization Done ! <--");
    }
}
