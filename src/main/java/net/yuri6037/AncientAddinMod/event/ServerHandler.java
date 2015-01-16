package net.yuri6037.AncientAddinMod.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.yuri6037.AncientAddinMod.entity.AncientsUtil;
import net.yuri6037.AncientAddinMod.entity.ExtendedPlayer;
import net.yuri6037.AncientAddinMod.items.ItemHeavyArmor;
import net.yuri6037.AncientAddinMod.items.ItemPersonalShield;

@SuppressWarnings("unused")
public class ServerHandler {

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
}
