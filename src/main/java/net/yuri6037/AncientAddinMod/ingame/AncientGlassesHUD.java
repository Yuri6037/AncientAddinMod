package net.yuri6037.AncientAddinMod.ingame;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.util.EntityFinder;
import net.yuri6037.AncientAddinMod.items.ItemList;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

import java.io.*;

public class AncientGlassesHUD extends GuiIngameRenderer {

    //private ResourceLocation location;

    public AncientGlassesHUD() {
        super("");

        //location = new ResourceLocation("AncientAddinMod:textures/gui/ancientView.png");
    }

    private float oldGammaSetting = -1F;

    public void renderGameOverlay() {
        ItemStack stack = mc.thePlayer.inventory.getStackInSlot(39);
        if (this.mc.gameSettings.thirdPersonView == 0 && stack != null && stack.getItem() == ItemList.ancientGlass && AncientsUtil.isPlayerAncient(mc.thePlayer) && stack.getItemDamage() < 128){
            if (oldGammaSetting == -1F) {
                oldGammaSetting = mc.gameSettings.gammaSetting;
                if (oldGammaSetting > 1F){
                    oldGammaSetting = readDataFromFile();
                } else {
                    saveDataToFile(oldGammaSetting);
                }
            }

            mc.gameSettings.gammaSetting = 1.5F;
            //drawScreenTexture(location);
            drawScreenAlphaColoredRect(0, 0, 90, 30, new int[]{0, 0, 0, 24});
            drawString(mc.fontRenderer, "World Info", 5, 5, 0x8385E1);

            //Time block
            int hours = (int) (mc.theWorld.getWorldTime() / 1000L % 24L + 6L);
            if (hours > 23) {
                hours = hours - 29 + 5;
            }
            float mins = (float) (mc.theWorld.getWorldTime() % 24000L);
            mins %= 1000.0F;
            mins = mins / 1000.0F * 60.0F;
            int mins2 = (int) mins;
            drawString(mc.fontRenderer, (new StringBuilder()).append("Time : ").append(hours).append(":").append(mins2).toString(), 5, 20, 0xE6FF00);

            ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            int j = resolution.getScaledWidth();
            int k = resolution.getScaledHeight();

            //Battery block
            drawScreenAlphaColoredRect(16, k / 2 - 64, 16, 64, new int[]{133, 133, 133, 32});
            float height = (128 - stack.getItemDamage()) * 64 / 128;
            drawScreenAlphaColoredRect(16, k / 2 - 64, 16, height, new int[]{0, 0, 255, 16});

            if (mc.objectMouseOver != null) {
                EntityLivingBase e = EntityFinder.findLookedAtEntity(mc.thePlayer, 10);
                if (e != null) {
                    drawScreenAlphaColoredRect(j / 2 - 64, k / 2 - 25, 128, 50, new int[]{0, 0, 0, 24});

                    if (e instanceof EntityMob){
                        String name = EntityList.getEntityString(e);
                        if (name != null) {
                            drawString(mc.fontRenderer, name, j / 2 - mc.fontRenderer.getStringWidth(name) / 2, k / 2 - 23, 0xFF0000);
                        } else {
                            drawString(mc.fontRenderer, e.getClass().getSimpleName(), j / 2 - mc.fontRenderer.getStringWidth(e.getClass().getSimpleName()) / 2, k / 2 - 23, 0xFF0000);
                        }
                    } else {
                        String name = EntityList.getEntityString(e);
                        if (name != null) {
                            drawString(mc.fontRenderer, name, j / 2 - mc.fontRenderer.getStringWidth(name) / 2, k / 2 - 23, 0xE6FF00);
                        } else {
                            drawString(mc.fontRenderer, e.getClass().getSimpleName(), j / 2 - mc.fontRenderer.getStringWidth(e.getClass().getSimpleName()) / 2, k / 2 - 23, 0xE6FF00);
                        }
                    }

                    String str = e.getHealth() + "/" + e.getMaxHealth();
                    drawString(mc.fontRenderer, str, j / 2 - mc.fontRenderer.getStringWidth(str) / 2, k / 2 - 1, 0xE6FF00);
                    int w = (int) (e.getHealth() * 120 / e.getMaxHealth());
                    drawScreenAlphaColoredRect(j / 2 - 59, k / 2 - 1, w, 8, new int[]{0, 255, 0, 16});
                }
            }

            GL11.glColor4f(1, 1, 1, 1);
        } else {
            if (oldGammaSetting != -1F){
                mc.gameSettings.gammaSetting = oldGammaSetting;
                mc.gameSettings.saveOptions();
                oldGammaSetting = -1F;
                deleteDataFile();
            }
        }
    }

    private void saveDataToFile(float f){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(".", "savedGamma.txt")));
            writer.write(String.valueOf(f));
            writer.close();
        } catch (IOException e) {
            LogManager.getLogger().warn("Unable to change savedGamma.txt !");
        }
    }

    private void deleteDataFile(){
        File f = new File(".", "savedGamma.txt");
        if (f.delete()) {
            LogManager.getLogger().warn("Deleted savedGamma.txt !");
        } else {
            LogManager.getLogger().warn("Unable to delete savedGamma.txt !");
        }
    }

    private float readDataFromFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(".", "savedGamma.txt")));
            float f = Float.parseFloat(reader.readLine());
            reader.close();
            if (f > 1F){
                return 0.0F;
            }
            return f;
        } catch (IOException e) {
            LogManager.getLogger().warn("Unable to read savedGamma.txt !");
        }
        return 0.0F;
    }

    public void updateGameOverlay() {
    }
}
