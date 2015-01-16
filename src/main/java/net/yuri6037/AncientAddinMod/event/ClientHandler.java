package net.yuri6037.AncientAddinMod.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.entity.ExtendedPlayer;
import net.yuri6037.AncientAddinMod.ingame.*;
import net.yuri6037.AncientAddinMod.items.ItemHeavyArmor;
import net.yuri6037.AncientAddinMod.items.ItemPersonalShield;
import net.yuri6037.AncientAddinMod.packet.PacketManager;
import net.yuri6037.AncientAddinMod.enums.EnumHeavyArmorActions;
import net.yuri6037.AncientAddinMod.enums.EnumHeavyArmorFeature;
import net.yuri6037.AncientAddinMod.packet.data.PacketHeavyArmor;

@SideOnly(Side.CLIENT)
@SuppressWarnings("unused")
public class ClientHandler {

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent event){
        IngameRendererHookList.onRender();
    }

    @SubscribeEvent
    public void updateGameOverlay(TickEvent event){
        if (event.type == TickEvent.Type.CLIENT) {
            IngameRendererHookList.onUpdate();
        }
    }

    @SubscribeEvent
    public void onFogUpdate(EntityViewRenderEvent.FogDensity event){
        //event.density = 0.0F;
        //event.setCanceled(true);
    }

    @SubscribeEvent
    public void onDrawHighlight(DrawBlockHighlightEvent event){

    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        if (event.entity instanceof EntityPlayer) {
            if (ItemHeavyArmor.canPlayerGetEffects((EntityPlayer) event.entity) && event.source.damageType.equals("fall")) {
                event.ammount = event.ammount / 4;
                EntityPlayer player = (EntityPlayer) event.entity;
                ItemHeavyArmor.damageArmor(player, (int) event.ammount);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDamage(LivingAttackEvent event){
        if (event.entity instanceof EntityPlayer){
            DamageSource source = event.source;
            int amount = (int) event.ammount;
            EntityPlayer player = (EntityPlayer) event.entity;
            if (!ItemPersonalShield.canPlayerTakeDamage(player, source)){
                ItemPersonalShield.pumpItemBattery(player, source, amount);
                player.setFire(0);
                event.setCanceled(true);
                return;
            }
        }
        event.setCanceled(false);
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity != null && event.entity instanceof EntityPlayer) {
            EntityPlayer ply = (EntityPlayer) event.entity;
            if (AncientsUtil.needRegistrance(ply)){
                AncientsUtil.setPlayerExtension(ply);
            }
        }
    }

    @SubscribeEvent
    public void onEntityConstructed(EntityJoinWorldEvent event){
        if (event.entity instanceof EntityPlayer){
            ExtendedPlayer player = (ExtendedPlayer) event.entity.getExtendedProperties(ExtendedPlayer.extensionName);
            player.sendPacketUpdate();
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().theWorld.isRemote) {
            if (KeyRegistry.heavyArmorTotalScreenToggleKey.isPressed()) {
                EntityPlayer ply = Minecraft.getMinecraft().thePlayer;
                boolean b = ItemHeavyArmor.armorHasFeatureEnabled(ply, EnumHeavyArmorFeature.TOTALSCREEN);
                if (b) {
                    PacketManager.sendPacketToServer(new PacketHeavyArmor(EnumHeavyArmorActions.TOTALSCREEN_OPEN));
                } else {
                    PacketManager.sendPacketToServer(new PacketHeavyArmor(EnumHeavyArmorActions.TOTALSCREEN_CLOSE));
                }
            } else if (KeyRegistry.heavyArmorLighToggleKey.isPressed()) {
                EntityPlayer ply = Minecraft.getMinecraft().thePlayer;
                boolean b = ItemHeavyArmor.armorHasFeatureEnabled(ply, EnumHeavyArmorFeature.NIGHT_VISION);
                if (b) {
                    PacketManager.sendPacketToServer(new PacketHeavyArmor(EnumHeavyArmorActions.LIGHTSYSTEM_OFF));
                } else {
                    PacketManager.sendPacketToServer(new PacketHeavyArmor(EnumHeavyArmorActions.LIGHTSYSTEM_ON));
                }
            } else if (KeyRegistry.heavyArmorPresurizeToggleKey.isPressed()) {
                EntityPlayer ply = Minecraft.getMinecraft().thePlayer;
                boolean b = ItemHeavyArmor.armorHasFeatureEnabled(ply, EnumHeavyArmorFeature.PRESURIZATION);
                if (b) {
                    PacketManager.sendPacketToServer(new PacketHeavyArmor(EnumHeavyArmorActions.UNPRESURIZE));
                } else {
                    PacketManager.sendPacketToServer(new PacketHeavyArmor(EnumHeavyArmorActions.PRESURIZE));
                }
            }
        }
    }

    static {
        IngameRendererHookList.registerIngameHook(new HandDroneLauncherHUD());
        IngameRendererHookList.registerIngameHook(new AncientGlassesHUD());
        IngameRendererHookList.registerIngameHook(new PersonalShieldHUD());
        IngameRendererHookList.registerIngameHook(new HandRayonThrowerHUD());
        IngameRendererHookList.registerIngameHook(new HeavyHelmetHUD());
    }
}
