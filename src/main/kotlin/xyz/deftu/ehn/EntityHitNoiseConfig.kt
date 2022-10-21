package xyz.deftu.ehn

import gg.essential.universal.ChatColor
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import net.minecraft.launchwrapper.Launch
import java.io.File

private val configFile by lazy {
    val configDir = File(Launch.minecraftHome, "config")
    if (!configDir.exists() || configDir.mkdirs())
        throw IllegalStateException("Could not create config directory - This shouldn't happen... (it should already exist?!)")
    File(configDir, "${EntityHitNoise.ID}.toml")
}

object EntityHitNoiseConfig : Vigilant(
    file = configFile,
    guiTitle = "${ChatColor.GOLD}@MOD_NAME@"
) {
    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle",
        category = "General"
    ) @JvmStatic var toggle = true
    @Property(
        type = PropertyType.SWITCH,
        name = "Player Toggle",
        category = "General"
    ) @JvmStatic var playerToggle = true
    @Property(
        type = PropertyType.SWITCH,
        name = "Mob Toggle",
        category = "General"
    ) @JvmStatic var mobToggle = true

    @Property(
        type = PropertyType.TEXT,
        name = "Audio file",
        category = "Sound"
    ) private var file = ""
    @Property(
        type = PropertyType.SLIDER,
        name = "Audio volume",
        category = "Sound",
        min = 0,
        max = 1000
    ) @JvmStatic var volume = 1000

    init {
        initialize()
    }

    @JvmStatic
    fun getAudioFile() = File(file)
}
