package iRacingJVM.test

import iRacingJVM.iRacingSDK
import iRacingJVM.models.TelemetryData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import java.time.LocalDate
import java.time.LocalTime

class iRacingSDKTests {

    private lateinit var telemetryData: TelemetryData

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
        val sessionInfo = telemetryData.telemetrySession
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
        val weekendOptions = telemetryData.telemetrySession.weekendInfo.weekendOptions
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
        val telemetryOptions = telemetryData.telemetrySession.weekendInfo.telemetryOptions
        assertEquals("", telemetryOptions.telemetryDiskFile)
    }

    @Test
    fun sessionInfo(){
        assertNotNull(telemetryData.telemetrySession.sessionInfo)
    }

    @Test
    fun session() {
        val session = telemetryData.telemetrySession.sessionInfo.sessions.first()
        assertEquals(0, session.sessionNum)
        assertEquals("unlimited", session.sessionLaps )
        assertEquals("3600.0000 sec", session.sessionTime)
        assertEquals("Practice", session.sessionType)
        assertEquals("moderate usage", session.sessionTrackRubberState)
        assertEquals("PRACTICE", session.sessionName)
        assertNull(session.sessionSubType)
        assertEquals(false, session.sessionSkipped)
        assertEquals(1, session.sessionRunGroupsUsed)
        assertEquals(-1.0000, session.resultsAverageLapTime)
        assertEquals(0, session.resultsNumCautionFlags)
        assertEquals(0, session.resultsNumCautionLaps)
        assertEquals(0, session.resultsNumLeadChanges)
        assertEquals(-1, session.resultsLapsComplete)
        assertEquals(false, session.resultsOfficial)
    }

    @Test
    fun resultsPosition() {
        val resultsPosition = telemetryData.telemetrySession.sessionInfo.sessions.first().resultsPositions.first()
        assertEquals(1, resultsPosition.position)
        assertEquals(0, resultsPosition.classPosition)
        assertEquals(0, resultsPosition.carIdx)
        assertEquals(3, resultsPosition.lap)
        assertEquals(122.8899, resultsPosition.time)
        assertEquals(3, resultsPosition.fastestLap)
        assertEquals(122.8899, resultsPosition.fastestTime)
        assertEquals(122.8899, resultsPosition.lastTime)
        assertEquals(0, resultsPosition.lapsLed)
        assertEquals(3, resultsPosition.lapsComplete)
        assertEquals(0.0, resultsPosition.lapsDriven)
        assertEquals(0, resultsPosition.incidents)
        assertEquals(0, resultsPosition.reasonOutId)
        assertEquals("Running", resultsPosition.reasonOutStr)
    }

    @Test
    fun resultsFastestLap() {
        val fastestLap = telemetryData.telemetrySession.sessionInfo.sessions.first().resultsFastestLap.first()
        assertEquals(0, fastestLap.carIdx)
        assertEquals(3, fastestLap.fastestLap)
        assertEquals(122.8899, fastestLap.fastestTime)
    }

    @Test
    fun cameraInfo() {
        val cameraInfo = telemetryData.telemetrySession.cameraInfo
        assertEquals(20, cameraInfo.groups.size)
        val firstGroup = cameraInfo.groups.first()
        assertEquals("Nose", firstGroup.groupName)
        assertEquals(1, firstGroup.cameras.size)
        val firstCamera = firstGroup.cameras.first()
        assertEquals(1, firstCamera.cameraNum)
        assertEquals("CamNose", firstCamera.cameraName)
    }

    @Test
    fun radioInfo() {
        val radioInfo = telemetryData.telemetrySession.radioInfo
        assertEquals(0, radioInfo.selectedRadioNum)
        val radio = radioInfo.radios.single()
        assertEquals(0, radio.radioNum)
        assertEquals(2, radio.hopCount)
        assertEquals(6, radio.numFrequencies)
        assertEquals(0, radio.tunedToFrequencyNum)
        assertEquals(true, radio.scanningIsOn)

        assertEquals(6, radio.frequencies.size)
        val firstFrequency = radio.frequencies.first()
        assertEquals(0, firstFrequency.frequencyNum)
        assertEquals("@ALLTEAMS", firstFrequency.frequencyName)
        assertEquals(12, firstFrequency.priority)
        assertEquals(-1, firstFrequency.carIdx)
        assertEquals(0, firstFrequency.clubId)
        assertEquals(true, firstFrequency.canScan)
        assertEquals(true, firstFrequency.canSquawk)
        assertEquals(false, firstFrequency.muted)
        assertEquals(true, firstFrequency.isMutable)
        assertEquals(false, firstFrequency.isDeletable)
    }

    @Test
    fun driverInfo() {
        val driverInfo = telemetryData.telemetrySession.driverInfo
        assertEquals(10, driverInfo.driverCarIdx)
        assertEquals(557322, driverInfo.driverUserId)
        assertEquals(-1, driverInfo.paceCarIdx)
        assertEquals(-0.416, driverInfo.driverHeadPosX)
        assertEquals(0.344, driverInfo.driverHeadPosY)
        assertEquals(0.660, driverInfo.driverHeadPosZ)
        assertEquals(875.000, driverInfo.driverCarIdleRPM)
        assertEquals(7525.000, driverInfo.driverCarRedLine)
        assertEquals(4, driverInfo.driverCarEngCylinderCount)
        assertEquals(0.750, driverInfo.driverCarFuelKgPerLtr)
        assertEquals(44.987, driverInfo.driverCarFuelMaxLtr)
        assertEquals(1.000, driverInfo.driverCarMaxFuelPct)
        assertEquals(5600.000, driverInfo.driverCarSLFirstRPM)
        assertEquals(7200.000, driverInfo.driverCarSLShiftRPM)
        assertEquals(7200.000, driverInfo.driverCarSLLastRPM)
        assertEquals(7700.000, driverInfo.driverCarSLBlinkRPM)
        assertEquals("2020.09.16.02", driverInfo.driverCarVersion)
        assertEquals(0.012511, driverInfo.driverPitTrkPct)
        assertEquals(117.1841, driverInfo.driverCarEstLapTime)
        assertEquals("baseline.sto", driverInfo.driverSetupName)
        assertEquals(false, driverInfo.driverSetupIsModified)
        assertEquals("fixed", driverInfo.driverSetupLoadTypeName)
        assertEquals(true, driverInfo.driverSetupPassedTech)
        assertEquals(0, driverInfo.driverIncidentCount)
    }

    @Test
    fun driver() {
        assertEquals(17, telemetryData.telemetrySession.driverInfo.drivers.size)
        val driver = telemetryData.telemetrySession.driverInfo.drivers.first()
        assertEquals(0, driver.carIdx)
        assertEquals("Andrew Pratt", driver.userName)
        assertEquals("AP", driver.initials)
        assertEquals(443347, driver.userId)
        assertEquals(0, driver.teamId)
        assertEquals("Andrew Pratt", driver.teamName)
        assertEquals("1", driver.carNumber)
        assertEquals(1, driver.carNumberRaw)
        assertEquals("mx5 mx52016", driver.carPath)
        assertEquals(74, driver.carClassId)
        assertEquals(67, driver.carId)
        assertEquals(false, driver.carIsPaceCar)
        assertEquals(false, driver.carIsAI)
        assertEquals("Mazda MX-5 Cup", driver.carScreenName)
        assertEquals("MX-5 Cup", driver.carScreenNameShort)
        assertEquals("MX5 Cup 2016", driver.carClassShortName)
        assertEquals(33, driver.carClassRelSpeed)
        assertEquals(0, driver.carClassLicenseLevel)
        assertEquals("1.000 %", driver.carClassMaxFuelPct)
        assertEquals("0.000 kg", driver.carClassWeightPenalty)
        assertEquals("0.000 %", driver.carClassPowerAdjust)
        assertEquals("0 %", driver.carClassDryTireSetLimit)
        assertEquals("0xffffff", driver.carClassColor)
        assertEquals(1350, driver.iRating)
        assertEquals(2, driver.licLevel)
        assertEquals(250, driver.licSubLevel)
        assertEquals("R 2.50", driver.licString)
        assertEquals("0xfc0706", driver.licColor)
        assertEquals(false, driver.isSpectator)
        assertEquals("1,00aeef,0a0a0a,f26522", driver.carDesignStr)
        assertEquals("68,2f2f30,0a0a0a,181818", driver.helmetDesignStr)
        assertEquals("1,ffffff,333333,040407", driver.suitDesignStr)
        assertEquals("0,0,ffffff,777777,000000", driver.carNumberDesignStr)
        assertEquals(0, driver.carSponsor1)
        assertEquals(0, driver.carSponsor2)
        assertEquals("New England", driver.clubName)
        assertEquals("Division 1", driver.divisionName)
        assertEquals(-1, driver.curDriverIncidentCount)
        assertEquals(-1, driver.teamIncidentCount)
    }

    @Test
    fun carSetup() {
        val setup = telemetryData.telemetrySession.carSetup
        assertEquals(1, setup.updateCount)

    }

    @Test
    fun suspension() {
        val suspension = telemetryData.telemetrySession.carSetup.suspension
        assertNotNull(suspension.leftFront)
        assertNotNull(suspension.leftRear)
        assertNotNull(suspension.rightFront)
        assertNotNull(suspension.rightRear)
    }

    @Test
    fun frontSetup() {
        val frontSetup = telemetryData.telemetrySession.carSetup.suspension.front
        assertEquals("-1 mm", frontSetup.toeIn)
        assertEquals("50.0%", frontSetup.crossWeight)
        assertEquals("Firm", frontSetup.antiRollBar)
    }

    @Test
    fun rearSetup() {
        val rearSetup = telemetryData.telemetrySession.carSetup.suspension.rear
        assertEquals("20.0 L", rearSetup.fuelLevel)
        assertEquals("+4 mm", rearSetup.toeIn)
        assertEquals("Unhooked", rearSetup.antiRollBar)
    }

    @Test
    fun tireSetup() {
        val frontRight = telemetryData.telemetrySession.carSetup.suspension.rightFront
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
