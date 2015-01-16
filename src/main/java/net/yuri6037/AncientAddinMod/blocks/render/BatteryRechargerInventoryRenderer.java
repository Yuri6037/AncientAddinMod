package net.yuri6037.AncientAddinMod.blocks.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class BatteryRechargerInventoryRenderer extends BlockRenderer {

    private ModelBatteryRecharger invModel;
    private ResourceLocation location;

    public BatteryRechargerInventoryRenderer() {
        super(0, true);

        invModel = new ModelBatteryRecharger();
        location = new ResourceLocation("AncientAddinMod:textures/entity/ModelBatteryRecharger.png");
    }

    public void doInventoryBlockRendering(RenderBlocks renderer, int metadata, Block b) {
        Minecraft.getMinecraft().renderEngine.bindTexture(location);

        GL11.glRotatef(-90F, 0, 1, 0);
        GL11.glTranslatef(0, -1F, 0);
        invModel.render(null, 45.0F, 0.0F, -0.1F, 90F, 0.0F, 0.0625F);
    }

    public void doWorldBlockRendering(IBlockAccess world, int x, int y, int z, RenderBlocks renderer, Block b) {
    }
}
