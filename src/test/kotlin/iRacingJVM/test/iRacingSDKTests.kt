package iRacingJVM.test

import iRacingJVM.iRacingSDK
import iRacingJVM.models.TelemetryData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import java.time.LocalDate
import java.time.LocalTime

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
        val sessionInfo = telemetryData.session
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

    @Test
    fun weekendOptions() {
        val weekendOptions = telemetryData.session.weekendInfo.weekendOptions
        assertEquals(12, weekendOptions.numStarters)
        assertEquals("2x2 inline pole on left", weekendOptions.startingGrid)
        assertEquals("best lap", weekendOptions.qualifyScoring)
        assertEquals("off", weekendOptions.courseCautions)
        assertEquals(true, weekendOptions.standingStart)
        assertEquals(false, weekendOptions.shortParadeLap)
        assertEquals("single file", weekendOptions.restarts)
        assertEquals("Specified / Dynamic Sky", weekendOptions.weatherType)
        assertEquals("Partly Cloudy", weekendOptions.skies)
        assertEquals("N", weekendOptions.windDirection)
        assertEquals("3.22 km/h", weekendOptions.windSpeed)
        assertEquals("25.56 C", weekendOptions.weatherTemp)
        assertEquals("55 %", weekendOptions.relativeHumidity)
        assertEquals("0 %", weekendOptions.fogLevel)
        assertEquals(LocalTime.of(13, 40), weekendOptions.timeOfDay)
        assertEquals(LocalDate.parse("2019-05-20"), weekendOptions.date)
        assertEquals(1, weekendOptions.earthRotationSpeedupFactor)
        assertEquals(false, weekendOptions.unofficial)
        assertEquals("consumer", weekendOptions.commercialMode)
        assertEquals("variable", weekendOptions.nightMode)
        assertEquals(true, weekendOptions.isFixedSetup)
        assertEquals("default", weekendOptions.strictLapsChecking)
        assertEquals(true, weekendOptions.hasOpenRegistration)
        assertEquals(1, weekendOptions.hardcoreLevel)
        assertEquals(0, weekendOptions.numJokerLaps)
        assertEquals("unlimited", weekendOptions.incidentLimit)
        assertEquals(1, weekendOptions.fastRepairsLimit)
        assertEquals(0, weekendOptions.greenWhiteCheckeredLimit)
    }

    @Test
    fun telemetryOptions() {
        val telemetryOptions = telemetryData.session.weekendInfo.telemetryOptions
        assertEquals("", telemetryOptions.telemetryDiskFile)
    }

    @Test
    fun carSetup() {
        val setup = telemetryData.session.carSetup
        assertEquals(1, setup.updateCount)
    }

    @Test
    fun suspension() {
        val suspension = telemetryData.session.carSetup.suspension
        assertNotNull(suspension.leftFront)
        assertNotNull(suspension.leftRear)
        assertNotNull(suspension.rightFront)
        assertNotNull(suspension.rightRear)
    }

    @Test
    fun frontSetup() {
        val frontSetup = telemetryData.session.carSetup.suspension.front
        assertEquals("-1 mm", frontSetup.toeIn)
        assertEquals("50.0%", frontSetup.crossWeight)
        assertEquals("Firm", frontSetup.antiRollBar)
    }

    @Test
    fun rearSetup() {
        val rearSetup = telemetryData.session.carSetup.suspension.rear
        assertEquals("20.0 L", rearSetup.fuelLevel)
        assertEquals("+4 mm", rearSetup.toeIn)
        assertEquals("Unhooked", rearSetup.antiRollBar)
    }

    @Test
    fun tireSetup() {
        val frontRight = telemetryData.session.carSetup.suspension.rightFront
        assertEquals("207 kPa", frontRight.coldPressure)
        assertEquals("207 kPa", frontRight.lastHotPressure)
        assertEquals("39C, 39C, 39C", frontRight.lastTempsOMI)
        assertEquals("100%, 100%, 100%", frontRight.treadRemaining)
        assertEquals("2455 N", frontRight.cornerWeight)
        assertEquals("120 mm", frontRight.rideHeight)
        assertEquals("71 mm", frontRight.springPerchOffset)
        assertEquals("+10 clicks", frontRight.bumpStiffness)
        assertEquals("+8 clicks", frontRight.reboundStiffness)
        assertEquals("-2.7 deg", frontRight.camber)
    }
}
