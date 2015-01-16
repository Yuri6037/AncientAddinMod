package net.yuri6037.AncientAddinMod.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.yuri6037.AncientAddinMod.blocks.inventory.ContainerItemDisassembler;
import net.yuri6037.AncientAddinMod.blocks.inventory.TileEntityItemDisassembler;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiItemDisassembler extends GuiContainer {

    private ResourceLocation location;

    public GuiItemDisassembler(InventoryPlayer player, TileEntityItemDisassembler te) {
        super(new ContainerItemDisassembler(player, te));

        location = new ResourceLocation("AncientAddinMod:textures/gui/decompiler.png");
    }

    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        mc.fontRenderer.drawString(StatCollector.translateToLocal("container.decompiler"), 8, 6, 4210752);
        mc.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(location);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
