package xyz.deftu.ehn.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.ehn.EntityHitNoise;
import xyz.deftu.ehn.EntityHitNoiseConfig;
import xyz.deftu.ehn.sound.SoundPlayer;

@Mixin({EntityLivingBase.class})
public class EntityLivingBaseMixin {
    @Inject(method = "damageEntity", at = @At("HEAD"))
    private void ehn$onEntityDamaged(DamageSource damageSrc, float damageAmount, CallbackInfo ci) {
        EntityLivingBase entity = (EntityLivingBase) (Object) this;
        if (!EntityHitNoiseConfig.getToggle() || entity.isEntityInvulnerable(damageSrc) || damageSrc.getSourceOfDamage() == null || !damageSrc.getSourceOfDamage().getUniqueID().equals(Minecraft.getMinecraft().getSession().getProfile().getId()))
            return;
        if (!EntityHitNoise.hasSound()) {
            EntityHitNoise.notifyNoSound();
            return;
        }

        if (entity instanceof EntityPlayer && !EntityHitNoiseConfig.getPlayerToggle())
            return;
        else if (!(entity instanceof EntityPlayer) && !EntityHitNoiseConfig.getMobToggle())
            return;

        SoundPlayer.play(EntityHitNoiseConfig.getAudioFile(), EntityHitNoiseConfig.getVolume());
    }
}
