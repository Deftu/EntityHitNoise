package xyz.deftu.ehn.sound

import xyz.deftu.ehn.EntityHitNoise
import java.io.File
import javax.sound.sampled.*


class DefaultSoundHandler : SoundHandler {
    override fun play(file: File, volume: Int) {
        if (!file.exists() || volume == 0)
            return

        try {
            val stream = AudioSystem.getAudioInputStream(file)
            val clip = AudioSystem.getClip()
            clip.addLineListener { event ->
                if (event.type == LineEvent.Type.STOP) {
                    clip.close()
                    stream.close()
                }
            }

            clip.open(stream)
            val control = clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
            control.value = (volume - 1000f).coerceIn(control.minimum, control.maximum)
            clip.start()
            clip.drain()
        } catch (e: Exception) {
            e.printStackTrace()
            EntityHitNoise.send("An exception occurred playing a sound! Please report this. (${e::class.java.simpleName})")
        }
    }
}
