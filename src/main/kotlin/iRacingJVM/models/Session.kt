package iRacingJVM.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.LocalTime

class Session(@JsonProperty("WeekendInfo")val weekendInfo: WeekendInfo, @JsonProperty("CarSetup") val carSetup: CarSetup)

data class WeekendInfo(
    @JsonProperty("TrackName")val trackName: String,
    @JsonProperty("TrackID")val trackId: Int,
    @JsonProperty("TrackLength")val trackLength: String,
    @JsonProperty("TrackDisplayName")val trackDisplayName: String,
    @JsonProperty("TrackDisplayShortName")val trackDisplayShortName: String,
    @JsonProperty("TrackConfigName")val trackConfigName: String,
    @JsonProperty("TrackCity")val trackCity: String,
    @JsonProperty("TrackCountry")val trackCountry: String,
    @JsonProperty("TrackAltitude")val trackAltitude: String,
    @JsonProperty("TrackLatitude")val trackLatitude: String,
    @JsonProperty("TrackLongitude")val trackLongitude: String,
    @JsonProperty("TrackNorthOffset")val trackNorthOffset: String,
    @JsonProperty("TrackNumTurns")val trackNumTurns: Int,
    @JsonProperty("TrackPitSpeedLimit")val trackPitSpeedLimit: String,
    @JsonProperty("TrackType")val trackType: String,
    @JsonProperty("TrackDirection")val trackDirection: String,
    @JsonProperty("TrackWeatherType")val trackWeatherType: String,
    @JsonProperty("TrackSkies")val trackSkies: String,
    @JsonProperty("TrackSurfaceTemp")val trackSurfaceTemp: String,
    @JsonProperty("TrackAirTemp")val trackAirTemp: String,
    @JsonProperty("TrackAirPressure")val trackAirPressure: String,
    @JsonProperty("TrackWindVel")val trackWindVel: String,
    @JsonProperty("TrackWindDir")val trackWindDir: String,
    @JsonProperty("TrackRelativeHumidity")val trackRelativeHumidity: String,
    @JsonProperty("TrackFogLevel")val trackFogLevel: String,
    @JsonProperty("TrackCleanup") @JsonFormat(shape = JsonFormat.Shape.NUMBER)val trackCleanup: Boolean,
    @JsonProperty("TrackDynamicTrack") @JsonFormat(shape = JsonFormat.Shape.NUMBER)val trackDynamicTrack: Boolean,
    @JsonProperty("TrackVersion")val trackVersion: String,
    @JsonProperty("SeriesID")val seriesID: Int,
    @JsonProperty("SeasonID")val seasonID: Int,
    @JsonProperty("SessionID")val sessionID: Int,
    @JsonProperty("SubSessionID")val subSessionID: Int,
    @JsonProperty("LeagueID")val leagueID: Int,
    @JsonProperty("Official") @JsonFormat(shape = JsonFormat.Shape.NUMBER)val official: Boolean,
    @JsonProperty("RaceWeek")val raceWeek: Int,
    @JsonProperty("EventType")val eventType: String,
    @JsonProperty("Category")val category: String,
    @JsonProperty("SimMode")val simMode: String,
    @JsonProperty("TeamRacing") @JsonFormat(shape = JsonFormat.Shape.NUMBER) val teamRacing: Boolean,
    @JsonProperty("MinDrivers") val minDrivers: Int,
    @JsonProperty("MaxDrivers") val maxDrivers: Int,
    @JsonProperty("DCRuleSet") val dCRuleSet: String,
    @JsonProperty("QualifierMustStartRace")@JsonFormat(shape = JsonFormat.Shape.NUMBER) val qualifierMustStartRace: Boolean,
    @JsonProperty("NumCarClasses") val numCarClasses: Int,
    @JsonProperty("NumCarTypes") val numCarTypes: Int,
    @JsonProperty("HeatRacing")@JsonFormat(shape = JsonFormat.Shape.NUMBER) val heatRacing: Boolean,
    @JsonProperty("BuildType") val buildType: String,
    @JsonProperty("BuildTarget") val buildTarget: String,
    @JsonProperty("BuildVersion") val buildVersion: String,
    @JsonProperty("WeekendOptions") val weekendOptions: WeekendOptions,
    @JsonProperty("TelemetryOptions") val telemetryOptions: TelemetryOptions,
    )
data class WeekendOptions(
    @JsonProperty("NumStarters") val numStarters: Int,
    @JsonProperty("StartingGrid") val startingGrid: String,
    @JsonProperty("QualifyScoring") val qualifyScoring: String,
    @JsonProperty("CourseCautions") val courseCautions: String,
    @JsonProperty("StandingStart")@JsonFormat(shape = JsonFormat.Shape.NUMBER) val standingStart: Boolean,
    @JsonProperty("ShortParadeLap")@JsonFormat(shape = JsonFormat.Shape.NUMBER) val shortParadeLap: Boolean,
    @JsonProperty("Restarts") val restarts: String,
    @JsonProperty("WeatherType") val weatherType: String,
    @JsonProperty("Skies") val skies: String,
    @JsonProperty("WindDirection") val windDirection: String,
    @JsonProperty("WindSpeed") val windSpeed: String,
    @JsonProperty("WeatherTemp") val weatherTemp: String,
    @JsonProperty("RelativeHumidity") val relativeHumidity: String,
    @JsonProperty("FogLevel") val fogLevel: String,
    @JsonProperty("TimeOfDay")@JsonFormat(pattern = "h:mm a",locale = "en_US", with = [JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_VALUES]) val timeOfDay: LocalTime,
    @JsonProperty("Date") val date: LocalDate,
    @JsonProperty("EarthRotationSpeedupFactor") val earthRotationSpeedupFactor: Int,
    @JsonProperty("Unofficial")@JsonFormat(shape = JsonFormat.Shape.NUMBER) val unofficial: Boolean,
    @JsonProperty("CommercialMode") val commercialMode: String,
    @JsonProperty("NightMode") val nightMode: String,
    @JsonProperty("IsFixedSetup")@JsonFormat(shape = JsonFormat.Shape.NUMBER) val isFixedSetup: Boolean,
    @JsonProperty("StrictLapsChecking")val strictLapsChecking: String,
    @JsonProperty("HasOpenRegistration")@JsonFormat(shape = JsonFormat.Shape.NUMBER) val hasOpenRegistration: Boolean,
    @JsonProperty("HardcoreLevel")val hardcoreLevel: Int,
    @JsonProperty("NumJokerLaps")val numJokerLaps: Int,
    @JsonProperty("IncidentLimit")val incidentLimit: String,
    @JsonProperty("FastRepairsLimit")val fastRepairsLimit: Int,
    @JsonProperty("GreenWhiteCheckeredLimit")val greenWhiteCheckeredLimit: Int,
    )

data class TelemetryOptions(
    @JsonProperty("TelemetryDiskFile")val telemetryDiskFile: String
)

data class CarSetup(
    @JsonProperty("UpdateCount") val updateCount: Int,
    @JsonProperty("Suspension") val suspension: Suspension,

)

data class Suspension(
    @JsonProperty("Front") val front: FrontSetup,
    @JsonProperty("LeftFront") val leftFront: LeftTireSetup,
    @JsonProperty("LeftRear") val leftRear: LeftTireSetup,
    @JsonProperty("RightFront") val rightFront: RightTireSetup,
    @JsonProperty("RightRear") val rightRear: RightTireSetup,
    @JsonProperty("Rear") val rear: RearSetup,
)

data class FrontSetup(
    @JsonProperty("ToeIn") val toeIn: String,
    @JsonProperty("CrossWeight") val crossWeight: String,
    @JsonProperty("AntiRollBar") val antiRollBar: String,
)

data class RearSetup(
    @JsonProperty("FuelLevel") val fuelLevel: String,
    @JsonProperty("ToeIn") val toeIn: String,
    @JsonProperty("AntiRollBar") val antiRollBar: String,
)

data class RightTireSetup(
    @JsonProperty("ColdPressure") val coldPressure: String,
    @JsonProperty("LastHotPressure") val lastHotPressure: String,
    @JsonProperty("LastTempsIMO") val lastTempsOMI: String,
    @JsonProperty("TreadRemaining") val treadRemaining: String,
    @JsonProperty("CornerWeight") val cornerWeight: String,
    @JsonProperty("RideHeight") val rideHeight: String,
    @JsonProperty("SpringPerchOffset") val springPerchOffset: String,
    @JsonProperty("BumpStiffness") val bumpStiffness: String,
    @JsonProperty("ReboundStiffness") val reboundStiffness: String,
    @JsonProperty("Camber") val camber: String,
)

data class LeftTireSetup(
    @JsonProperty("ColdPressure") val coldPressure: String,
    @JsonProperty("LastHotPressure") val lastHotPressure: String,
    @JsonProperty("LastTempsOMI") val lastTempsOMI: String,
    @JsonProperty("TreadRemaining") val treadRemaining: String,
    @JsonProperty("CornerWeight") val cornerWeight: String,
    @JsonProperty("RideHeight") val rideHeight: String,
    @JsonProperty("SpringPerchOffset") val springPerchOffset: String,
    @JsonProperty("BumpStiffness") val bumpStiffness: String,
    @JsonProperty("ReboundStiffness") val reboundStiffness: String,
    @JsonProperty("Camber") val camber: String,
)