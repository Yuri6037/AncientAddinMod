package net.yuri6037.AncientAddinMod.ingame;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.event.KeyRegistry;
import net.yuri6037.AncientAddinMod.items.ItemHeavyArmor;
import net.yuri6037.AncientAddinMod.items.ItemList;
import net.yuri6037.AncientAddinMod.packet.PacketManager;
import net.yuri6037.AncientAddinMod.enums.EnumHeavyArmorActions;
import net.yuri6037.AncientAddinMod.enums.EnumHeavyArmorFeature;
import net.yuri6037.AncientAddinMod.packet.data.PacketHeavyArmor;
import net.yuri6037.AncientAddinMod.util.EntityFinder;
import net.yuri6037.AncientAddinMod.enums.EntityType;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class HeavyHelmetHUD extends GuiIngameRenderer {

    private ResourceLocation location;

    private int ticks;
    private int keyboardPressTicks;

    private int switchToJetTicks;
    private boolean isUsingJet;

    private ResourceLocation helmet;
    private ResourceLocation body;
    private ResourceLocation legs;
    private ResourceLocation boots;

    public HeavyHelmetHUD() {
        super("");

        location = new ResourceLocation("AncientAddinMod:textures/gui/ancientView.png");

        helmet = new ResourceLocation("AncientAddinMod:textures/items/HeavyArmor_helmet.png");
        body = new ResourceLocation("AncientAddinMod:textures/items/HeavyArmor_body.png");
        legs = new ResourceLocation("AncientAddinMod:textures/items/HeavyArmor_legs.png");
        boots = new ResourceLocation("AncientAddinMod:textures/items/HeavyArmor_boots.png");
    }

    public float[] get2DFrom3D(float x, float y, float z, ScaledResolution resolution, EntityPlayer player) {
        int centerX = resolution.getScaledWidth() - 30;
        int centerY = resolution.getScaledHeight() - 30;

        Vec3 supperPos = Vec3.createVectorHelper(x, y, z);
        Vec3 playerPos = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
        Vec3 pos = playerPos.subtract(supperPos);
        //float rotation = (float) ((double) ((player.rotationYaw * 4F) / 360F) + 0.5D);
        //pos.rotateAroundY(-1 * (rotation));
        float x1 = (float) (centerX + pos.zCoord / 1);
        float y1 = (float) (centerY + 0.7 * pos.xCoord);
        return new float[]{x1, y1};
    }

    public void renderGameOverlay() {
        ItemStack stack = mc.thePlayer.inventory.getStackInSlot(39);

        if (this.mc.gameSettings.thirdPersonView == 0 && stack != null && stack.getItem() == ItemList.heavyArmor_helmet && AncientsUtil.isPlayerAncient(mc.thePlayer) && !ItemHeavyArmor.canUseArmor(mc.thePlayer) && ItemHeavyArmor.armorHasFeatureEnabled(mc.thePlayer, EnumHeavyArmorFeature.TOTALSCREEN) || stack != null && stack.getItem() == ItemList.heavyArmor_helmet && !AncientsUtil.isPlayerAncient(mc.thePlayer) && ItemHeavyArmor.armorHasFeatureEnabled(mc.thePlayer, EnumHeavyArmorFeature.TOTALSCREEN)){
            ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            drawScreenAlphaColoredRect(0, 0, resolution.getScaledWidth(), resolution.getScaledHeight(), new int[]{0, 0, 0, 255});
        }

        if (this.mc.gameSettings.thirdPersonView == 0 && stack != null && stack.getItem() == ItemList.heavyArmor_helmet && AncientsUtil.isPlayerAncient(mc.thePlayer) && ItemHeavyArmor.canUseArmor(mc.thePlayer) && ItemHeavyArmor.armorHasFeatureEnabled(mc.thePlayer, EnumHeavyArmorFeature.TOTALSCREEN)){
            Double d = ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, mc.entityRenderer, "cameraZoom", "field_78503_V");
            if (Keyboard.isKeyDown(KeyRegistry.heavyHelmetZoomKey.getKeyCode()) && d != 3.5D){
                ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.entityRenderer, 3.5D, "cameraZoom", "field_78503_V");
            } else if (!Keyboard.isKeyDown(KeyRegistry.heavyHelmetZoomKey.getKeyCode()) && d == 3.5D) {
                ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.entityRenderer, 1.0D, "cameraZoom", "field_78503_V");
            }

            drawScreenTexture(location);
            drawScreenAlphaColoredRect(0, 0, 128, 50, new int[]{0, 0, 0, 24});
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

            List<Entity> l = EntityFinder.findEntitiesInRayon(EntityType.LIVING, mc.thePlayer, 30, false);
            drawString(mc.fontRenderer, "Entities arround you : " + (l.size() - 1), 5, 30, 0xE6FF00);

            ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            int j = resolution.getScaledWidth();
            int k = resolution.getScaledHeight();

            //Drawing armor info
            ItemStack helmetItem = mc.thePlayer.inventory.armorItemInSlot(3);
            ItemStack bodyItem = mc.thePlayer.inventory.armorItemInSlot(2);
            ItemStack legsItem = mc.thePlayer.inventory.armorItemInSlot(1);
            ItemStack bootsItem = mc.thePlayer.inventory.armorItemInSlot(0);
            drawScreenAlphaColoredRect(0, k / 2 - 64, 16, 64, new int[]{0, 0, 0, 24});
            if (helmetItem != null) {
                drawTexturedRect(0, k / 2 - 64, 16, 16, helmet);
                int mxdmg = helmetItem.getMaxDamage();
                int dmg = mxdmg - helmetItem.getItemDamage();
                int displayDmg = (dmg * 16) / mxdmg;
                drawScreenAlphaColoredRect(0, k / 2 - 64, 16, 2, new int[]{133, 133, 133, 32});
                drawScreenAlphaColoredRect(0, k / 2 - 64, displayDmg, 2, new int[]{0, 255, 0, 16});
            }
            if (bodyItem != null) {
                drawTexturedRect(0, k / 2 - 48, 16, 16, body);
                int mxdmg = bodyItem.getMaxDamage();
                int dmg = mxdmg - bodyItem.getItemDamage();
                int displayDmg = (dmg * 16) / mxdmg;
                drawScreenAlphaColoredRect(0, k / 2 - 48, 16, 2, new int[]{133, 133, 133, 32});
                drawScreenAlphaColoredRect(0, k / 2 - 48, displayDmg, 2, new int[]{0, 255, 0, 16});
            }
            if (legsItem != null) {
                drawTexturedRect(0, k / 2 - 32, 16, 16, legs);
                int mxdmg = legsItem.getMaxDamage();
                int dmg = mxdmg - legsItem.getItemDamage();
                int displayDmg = (dmg * 16) / mxdmg;
                drawScreenAlphaColoredRect(0, k / 2 - 32, 16, 2, new int[]{133, 133, 133, 32});
                drawScreenAlphaColoredRect(0, k / 2 - 32, displayDmg, 2, new int[]{0, 255, 0, 16});
            }
            if (bootsItem != null) {
                drawTexturedRect(0, k / 2 - 16, 16, 16, boots);
                int mxdmg = bootsItem.getMaxDamage();
                int dmg = mxdmg - bootsItem.getItemDamage();
                int displayDmg = (dmg * 16) / mxdmg;
                drawScreenAlphaColoredRect(0, k / 2 - 16, 16, 2, new int[]{133, 133, 133, 32});
                drawScreenAlphaColoredRect(0, k / 2 - 16, displayDmg, 2, new int[]{0, 255, 0, 16});
            }

            //Battery block
            drawScreenAlphaColoredRect(16, k / 2 - 64, 16, 64, new int[]{133, 133, 133, 32});
            ItemStack body = mc.thePlayer.inventory.getStackInSlot(38);
            float height = body.stackTagCompound.getInteger("Battery") * 64 / 512;
            drawScreenAlphaColoredRect(16, k / 2 - 64, 16, height, new int[]{0, 0, 255, 16});

            if (helmetItem != null && bodyItem != null && legsItem != null && bootsItem != null){
                drawString(mc.fontRenderer, "READY", 0, k / 2 + 5, 0x008CFF);
                if (ItemHeavyArmor.armorHasFeatureEnabled(mc.thePlayer, EnumHeavyArmorFeature.NIGHT_VISION)) {
                    drawString(mc.fontRenderer, "NightVision Enabled", 0, k / 2 + 20, 0x008CFF);
                }
                if (ItemHeavyArmor.armorHasFeatureEnabled(mc.thePlayer, EnumHeavyArmorFeature.PRESURIZATION)) {
                    drawString(mc.fontRenderer, "Armor Presurized", 0, k / 2 + 30, 0x008CFF);
                }
            } else {
                drawString(mc.fontRenderer, "NOT READY", 0, k / 2 + 5, 0x008CFF);
            }

            //The map box
            drawScreenAlphaColoredRect(j - 64, k - 64, 64, 64, new int[]{133, 133, 133, 16});
            //The player point
            drawScreenAlphaColoredRect(j - 30, k - 30, 2, 2, new int[]{255, 255, 0, 255});
            //The entities points
            for (Entity e : l){
                if (e != mc.thePlayer) {
                    if (e instanceof EntityOtherPlayerMP || e instanceof EntityVillager) {
                        float[] xy = get2DFrom3D((float) e.posX, (float) e.posY, (float) e.posZ, resolution, mc.thePlayer);
                        float x = xy[0];
                        float y = xy[1];
                        drawScreenAlphaColoredRect(x, y, 2, 2, new int[]{0, 0, 255, 255});
                    } else if (e instanceof EntityMob){
                        float[] xy = get2DFrom3D((float) e.posX, (float) e.posY, (float) e.posZ, resolution, mc.thePlayer);
                        float x = xy[0];
                        float y = xy[1];
                        drawScreenAlphaColoredRect(x, y, 2, 2, new int[]{255, 0, 0, 255});
                    } else {
                        float[] xy = get2DFrom3D((float) e.posX, (float) e.posY, (float) e.posZ, resolution, mc.thePlayer);
                        float x = xy[0];
                        float y = xy[1];
                        drawScreenAlphaColoredRect(x, y, 2, 2, new int[]{0, 255, 0, 255});
                    }
                }
            }

            MovingObjectPosition obj = mc.thePlayer.rayTrace(30, 1.0F);
            Block b = mc.theWorld.getBlock(obj.blockX, obj.blockY, obj.blockZ);
            drawString(mc.fontRenderer, b.getLocalizedName(), 5, 40, 0xE6FF00);

            if (mc.objectMouseOver != null) {
                EntityLivingBase e = EntityFinder.findLookedAtEntity(mc.thePlayer, 30);
                if (e != null && !(e == mc.thePlayer)) {
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
        }
    }

    public void updateGameOverlay() {
        if (ItemHeavyArmor.canPlayerGetEffects(mc.thePlayer) && ItemHeavyArmor.armorHasFeatureEnabled(mc.thePlayer, EnumHeavyArmorFeature.TOTALSCREEN) && ItemHeavyArmor.canUseArmor(mc.thePlayer) && Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode()) && !mc.thePlayer.isInsideOfMaterial(Material.water)){
            if (isUsingJet) {
                keyboardPressTicks++;
                if (keyboardPressTicks == 2) {
                    mc.thePlayer.motionY += 0.1D;

                    ticks++;
                    if (ticks == 15) {
                        PacketManager.sendPacketToServer(new PacketHeavyArmor(EnumHeavyArmorActions.JET_USE));
                        ticks = 0;
                    }
                    keyboardPressTicks = 0;
                }
            } else {
                switchToJetTicks++;
                if (switchToJetTicks == 20){
                    isUsingJet = true;
                    switchToJetTicks = 0;
                }
            }
        } else {
            ticks = 0;

            if (mc.thePlayer != null && mc.thePlayer.onGround || mc.thePlayer != null && mc.thePlayer.isInsideOfMaterial(Material.water)) {
                isUsingJet = false;
                switchToJetTicks = 0;
            }
        }
    }
}
