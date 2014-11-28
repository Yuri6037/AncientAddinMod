package net.yuri6037.AncientAddinMod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import net.yuri6037.AncientAddinMod.blocks.BlockList;
import net.yuri6037.AncientAddinMod.blocks.render.BlockRenderList;
import net.yuri6037.AncientAddinMod.entity.EntityList;
import net.yuri6037.AncientAddinMod.entity.render.RenderList;
import net.yuri6037.AncientAddinMod.event.ClientHandler;
import net.yuri6037.AncientAddinMod.event.ServerHandler;
import net.yuri6037.AncientAddinMod.items.ItemList;
import net.yuri6037.AncientAddinMod.items.render.ItemRenderList;
import org.apache.logging.log4j.LogManager;

@Mod(modid = "AncientAddin", name = "AncientAddin Mod", version = AncientAddin.MOD_VERCION)
public class AncientAddin {
    public static final String MOD_VERCION = "InfDev";

    @Mod.Instance
    public static AncientAddin ancientAddin;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LogManager.getLogger().info("--> AncientAddin Initializing... <--");
        LogManager.getLogger().info("Adding Blocks...");
        new BlockList();
        LogManager.getLogger().info("Adding Items...");
        new ItemList();
        LogManager.getLogger().info("Adding Entities...");
        new EntityList();
        LogManager.getLogger().info("Adding ServerHandler...");
        MinecraftForge.EVENT_BUS.register(new ServerHandler());

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
            LogManager.getLogger().info("Adding Entities Renderers...");
            new RenderList();
            LogManager.getLogger().info("Adding Blocks Renderers...");
            new BlockRenderList();
            LogManager.getLogger().info("Adding Items Renderers...");
            new ItemRenderList();
            LogManager.getLogger().info("Adding ClientHandler...");
            MinecraftForge.EVENT_BUS.register(new ClientHandler());
        }
        LogManager.getLogger().info("--> Initializing Done ! <--");
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }
}
