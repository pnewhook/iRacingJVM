package iRacingJVM.test

import iRacingJVM.iRacingSDK
import iRacingJVM.models.TelemetryData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class iRacingSDKTests {

    lateinit var telemetryData: TelemetryData

    @BeforeEach
    fun setup() {
        val sdk = iRacingSDK();
        telemetryData = sdk.readFile(
            Paths.get(
                this.javaClass.getResource("/mx5 mx52016_oulton international 2020-10-12 19-52-39.ibt").toURI()
            ).toFile().absolutePath
        )
    }

    @Test
    fun `readFile should set header values`() {
        assertEquals(2, telemetryData.header.apiVersion)
        assertEquals(1, telemetryData.header.sdkStatus)
        assertEquals(60, telemetryData.header.tickRate)
        assertEquals(0, telemetryData.header.sessionInfoUpdate)
        assertEquals(27508, telemetryData.header.sessionInfoLength)
        assertEquals(33408, telemetryData.header.sessionInfoOffset)
        assertEquals(231, telemetryData.header.numVars)
        assertEquals(144, telemetryData.header.varHeaderOffset)
        assertEquals(1, telemetryData.header.numBuf)
        assertEquals(892, telemetryData.header.bufLength)
        assertEquals(746, telemetryData.header.varBuf.first().tickCount)
        assertEquals(60916, telemetryData.header.varBuf.first().bufOffset)
    }

    @Test
    fun `readFile should read sessionInfo`() {
        assertEquals("oulton international", telemetryData.sessionInfo.weekendInfo.trackName)
        assertEquals(180, telemetryData.sessionInfo.weekendInfo.trackId)
    }
}
