package xyz.deftu.ehn.sound

import java.io.File
import javax.sound.sampled.*


class DefaultSoundHandler : SoundHandler {
    private val BUFFER_SIZE = 2048

    override fun play(file: File, volume: Int) {
        if (volume == 0)
            return

        val stream = AudioSystem.getAudioInputStream(file)
        val format = stream.format
        val line = AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java, format)) as SourceDataLine
        line.open(format)
        line.addLineListener { event ->
            if (event.type == LineEvent.Type.STOP) {
                line.close()
                stream.close()
            }
        }

        val control = line.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
        control.value = (volume.toFloat()).coerceIn(control.minimum, control.maximum)

        var read = 0
        val data = ByteArray(BUFFER_SIZE)
        while (read != -1) {
            read = stream.read(data, 0, data.size)
            if (read >= 0) line.write(data, 0, read)
        }

        line.start()
        line.drain()
    }
}
