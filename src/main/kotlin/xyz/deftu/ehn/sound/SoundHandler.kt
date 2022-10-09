package xyz.deftu.ehn.sound

import java.io.File

interface SoundHandler {
    fun play(file: File, volume: Int)
}
