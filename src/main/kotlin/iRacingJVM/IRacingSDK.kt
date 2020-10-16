package iRacingJVM

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import iRacingJVM.models.SessionInfo
import iRacingJVM.models.TelemetryData
import iRacingJVM.models.iRacingSDKHeader
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Construct
import org.yaml.snakeyaml.constructor.Constructor
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
        val headerByteArray = ByteArray(HEADER_LENGTH)
        byteBuffer.get(headerByteArray, 0, HEADER_LENGTH)
        val header = iRacingSDKHeader(headerByteArray)

        val sessionInfoBytes = ByteArray(header.sessionInfoLength)
        byteBuffer.position(header.sessionInfoOffset)
        byteBuffer.get(sessionInfoBytes, 0, header.sessionInfoLength)
        val objectMapper = ObjectMapper(YAMLFactory())
        objectMapper.registerModule(KotlinModule())
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val sessionInfo = objectMapper.readValue(String(sessionInfoBytes), SessionInfo::class.java)

        return TelemetryData(header = header, sessionInfo = sessionInfo)
    }

    companion object {
        // Size of
        const val HEADER_LENGTH: Int = (Int.SIZE_BYTES * 12) + (Int.SIZE_BYTES * 4 * iRacingSDK.IRSDK_MAX_BUFS)
        const val IRSDK_MAX_BUFS: Int = 4
        const val IRSDK_MAX_STRING: Int = 32;
        const val IRSDK_MAX_DESC: Int = 64
        const val IRSDK_VER: Int = 2
    }
}
