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
        val sdk = iRacingSDK()
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
        val sessionInfo = telemetryData.sessionInfo
        assertEquals("oulton international", sessionInfo.weekendInfo.trackName)
        assertEquals(180, sessionInfo.weekendInfo.trackId)
        assertEquals("4.29 km", sessionInfo.weekendInfo.trackLength)
        assertEquals("Oulton Park Circuit", sessionInfo.weekendInfo.trackDisplayName)
        assertEquals("Oulton", sessionInfo.weekendInfo.trackDisplayShortName)
        assertEquals("International", sessionInfo.weekendInfo.trackConfigName)
        assertEquals("Little Budworth", sessionInfo.weekendInfo.trackCity)
        assertEquals("England", sessionInfo.weekendInfo.trackCountry)
        assertEquals("69.47 m", sessionInfo.weekendInfo.trackAltitude)
        assertEquals("53.180009 m", sessionInfo.weekendInfo.trackLatitude)
        assertEquals("-2.612779 m", sessionInfo.weekendInfo.trackLongitude)
        assertEquals("3.0267 rad", sessionInfo.weekendInfo.trackNorthOffset)
        assertEquals(17, sessionInfo.weekendInfo.trackNumTurns)
        assertEquals("49.97 kph", sessionInfo.weekendInfo.trackPitSpeedLimit)
        assertEquals("road course", sessionInfo.weekendInfo.trackType)
        assertEquals("neutral", sessionInfo.weekendInfo.trackDirection)
        assertEquals("Specified / Dynamic Sky", sessionInfo.weekendInfo.trackWeatherType)
        assertEquals("Partly Cloudy", sessionInfo.weekendInfo.trackSkies)
        assertEquals("39.80 C", sessionInfo.weekendInfo.trackSurfaceTemp)
        assertEquals("25.55 C", sessionInfo.weekendInfo.trackAirTemp)
        assertEquals("0.89 m/s", sessionInfo.weekendInfo.trackWindVel)
        assertEquals("0.00 rad", sessionInfo.weekendInfo.trackWindDir)
        assertEquals("55 %", sessionInfo.weekendInfo.trackRelativeHumidity)
        assertEquals("0 %", sessionInfo.weekendInfo.trackFogLevel)
        assertEquals(true, sessionInfo.weekendInfo.trackCleanup)
        assertEquals(true, sessionInfo.weekendInfo.trackDynamicTrack)
        assertEquals("2020.09.04.01", sessionInfo.weekendInfo.trackVersion)
        assertEquals(139, sessionInfo.weekendInfo.seriesID)
        assertEquals(2915, sessionInfo.weekendInfo.seasonID)
        assertEquals(139407965, sessionInfo.weekendInfo.sessionID)
        assertEquals(35053319, sessionInfo.weekendInfo.subSessionID)
        assertEquals(0, sessionInfo.weekendInfo.leagueID)
        assertEquals(true, sessionInfo.weekendInfo.official)
        assertEquals(4, sessionInfo.weekendInfo.raceWeek)
        assertEquals("Practice", sessionInfo.weekendInfo.eventType)
        assertEquals("Road", sessionInfo.weekendInfo.category)
        assertEquals("full", sessionInfo.weekendInfo.simMode)
        assertEquals(false, sessionInfo.weekendInfo.teamRacing)
        assertEquals(0, sessionInfo.weekendInfo.minDrivers)
        assertEquals(1, sessionInfo.weekendInfo.maxDrivers)
        assertEquals("None", sessionInfo.weekendInfo.dCRuleSet)
        assertEquals(false, sessionInfo.weekendInfo.qualifierMustStartRace)
        assertEquals(1, sessionInfo.weekendInfo.numCarClasses)
        assertEquals(1, sessionInfo.weekendInfo.numCarTypes)
        assertEquals(false, sessionInfo.weekendInfo.heatRacing)
        assertEquals("Release", sessionInfo.weekendInfo.buildType)
        assertEquals("Members", sessionInfo.weekendInfo.buildTarget)
        assertEquals("2020.10.07.02", sessionInfo.weekendInfo.buildVersion)
    }
}
