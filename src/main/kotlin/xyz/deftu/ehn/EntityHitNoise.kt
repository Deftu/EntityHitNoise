package xyz.deftu.ehn

import gg.essential.api.EssentialAPI
import gg.essential.universal.ChatColor
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import xyz.deftu.ehn.sound.SoundPlayer

@Mod(
    name = EntityHitNoise.NAME,
    modid = EntityHitNoise.ID,
    version = EntityHitNoise.VERSION,
    acceptedMinecraftVersions = "[1.8.9]",
    clientSideOnly = true,
    modLanguageAdapter = "gg.essential.api.utils.KotlinAdapter"
) object EntityHitNoise {
    const val NAME = "@MOD_NAME@"
    const val ID = "@MOD_ID@"
    const val VERSION = "@MOD_VERSION@"
    private val chatPrefix = ChatColor.translateAlternateColorCodes('&', "&8&l[&r&e$NAME&8&l]&r ")

    private var notifiedNoSound = false

    @Mod.EventHandler
    fun initialize(event: FMLInitializationEvent) {
        SoundPlayer.initialize()
        EntityHitNoiseCommand.register()
    }

    @JvmStatic
    fun send(message: String) {
        EssentialAPI.getMinecraftUtil().sendMessage(chatPrefix, message)
    }

    @JvmStatic
    fun hasSound() = EntityHitNoiseConfig.getAudioFile().exists()

    @JvmStatic
    fun notifyNoSound() {
        if (notifiedNoSound)
            return

        send("You don't have a valid sound file configured! The mod has no functionality unless you set this.")
        notifiedNoSound = true
    }
}
