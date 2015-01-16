package net.yuri6037.AncientAddinMod.ingame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

/**
 * Made by Yuri6037
 */
@SideOnly(Side.CLIENT)
public abstract class GuiIngameRenderer extends Gui {
    protected Minecraft mc;

    public GuiIngameRenderer(/*Param not used for the moment*/String disableID){
        mc = Minecraft.getMinecraft();
    }

	public abstract void renderGameOverlay();
    public abstract void updateGameOverlay();

    public void drawScreenTexture(ResourceLocation location){
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.1F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        mc.getTextureManager().bindTexture(location);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, (double) l, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)k, (double)l, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)k, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void drawTexturedRect(int x, int y, int w, int h, ResourceLocation location){
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.1F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        mc.getTextureManager().bindTexture(location);

        glTranslatef(0, 0, 0);
        glPushMatrix();
        glTranslatef(x, y, 0);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0, 0, -90.0D, 0.0D, 0.0D);
        tessellator.addVertexWithUV(0, h, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(w, h, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(w, 0, -90.0D, 1.0D, 0.0D);
        tessellator.draw();
        glPopMatrix();

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void drawScreenAlphaColoredRect(float x, float y, float w, float h, int[] color){
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_ALPHA_TEST);
        glEnable(GL_BLEND);
        glDisable(GL_LIGHTING);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glTranslatef(0, 0, 0);
        glPushMatrix();
        glTranslatef(x, y, 0);
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.setColorRGBA(color[0], color[1], color[2], color[3]);
        t.addVertex(0, 0, 0);
        t.addVertex(0, h, 0);
        t.addVertex(w, h, 0);
        t.addVertex(w, 0, 0);
        t.draw();
        glPopMatrix();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_ALPHA_TEST);
    }
}
