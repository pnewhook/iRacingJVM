package iRacingJVM

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import iRacingJVM.models.TelemetrySession
import iRacingJVM.models.TelemetryData
import iRacingJVM.models.iRacingSDKHeader
import java.nio.*
import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class iRacingSDK {
    fun readFile(path: String): TelemetryData {
        val fc = Files.newByteChannel(Paths.get(path), StandardOpenOption.READ) as FileChannel
        val byteBuffer: ByteBuffer = ByteBuffer.allocate(fc.size().toInt())
        fc.read(byteBuffer)
        byteBuffer.flip()
        return readTelemetryFileData(byteBuffer)
    }

    fun readFile(bytes: ByteArray): TelemetryData {
        val telemetryFileByteBuffer = ByteBuffer.wrap(bytes)
        return readTelemetryFileData(telemetryFileByteBuffer)
    }

    private fun readTelemetryFileData(byteBuffer: ByteBuffer): TelemetryData {
        val headerByteArray = ByteArray(HEADER_LENGTH)
        byteBuffer.get(headerByteArray, 0, HEADER_LENGTH)
        val header = iRacingSDKHeader(headerByteArray)

        val sessionInfoBytes = ByteArray(header.sessionInfoLength)
        byteBuffer.position(header.sessionInfoOffset)
        byteBuffer.get(sessionInfoBytes, 0, header.sessionInfoLength)
        val objectMapper = YAMLMapper.builder()
            .addModule(KotlinModule())
            .addModule(JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build()

        val sessionInfo = objectMapper.readValue(String(sessionInfoBytes), TelemetrySession::class.java)

        return TelemetryData(header = header, telemetrySession = sessionInfo)
    }

    companion object {
        // Size of
        const val IRSDK_MAX_BUFS: Int = 4
        const val HEADER_LENGTH: Int = (Int.SIZE_BYTES * 12) + (Int.SIZE_BYTES * 4 * IRSDK_MAX_BUFS)
        const val IRSDK_MAX_STRING: Int = 32
        const val IRSDK_MAX_DESC: Int = 64
        const val IRSDK_VER: Int = 2
    }
}
