package xyz.deftu.ehn

import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import xyz.deftu.ehn.sound.SoundPlayer

object AttackHandler {
    @JvmStatic
    fun handle(target: Entity) {
        if (
            !EntityHitNoiseConfig.toggle ||
            (target !is EntityPlayer && !EntityHitNoiseConfig.mobToggle) ||
            (target is EntityPlayer && !EntityHitNoiseConfig.playerToggle)
        ) return
        if (!EntityHitNoise.hasSound()) {
            EntityHitNoise.notifyNoSound()
            return
        }

        SoundPlayer.play(EntityHitNoiseConfig.getAudioFile(), EntityHitNoiseConfig.volume)
    }
}
