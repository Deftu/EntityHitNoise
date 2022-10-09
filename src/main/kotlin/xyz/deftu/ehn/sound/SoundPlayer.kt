package xyz.deftu.ehn.sound

import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

object SoundPlayer {
    private lateinit var soundHandlers: Map<String, SoundHandler>
    private val threadActions = CopyOnWriteArrayList<() -> Unit>()

    fun initialize() {
        soundHandlers = mapOf(
            "default" to DefaultSoundHandler()
        )

        Thread({
            while (true) {
                for (action in threadActions) {
                    action()
                    threadActions.remove(action)
                }
            }
        }, "SoundPlayer").start()
    }

    @JvmStatic
    fun play(file: File, volume: Int) {
        val handler = soundHandlers[file.extension] ?: soundHandlers["default"] ?: throw IllegalStateException("No default sound handler present? That shouldn't happen!")
        threadActions.add {
            handler.play(file, volume)
        }
    }
}
