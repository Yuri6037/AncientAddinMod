package net.yuri6037.AncientAddinMod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;

public class BlockList {

    public static final Block camera;
    public static final Block stazeModuleCore;

    private static Block registerBlock(String id, Block b){
        GameRegistry.registerBlock(b, "ancientAddin." + id);
        return b;
    }

    static {
        camera = registerBlock("camera", new BlockCamera());
        stazeModuleCore = registerBlock("stazeModuleCore", new BlockStazeModuleCore());
    }
}
