package iRacingJVM.models

import com.fasterxml.jackson.annotation.JsonProperty

class SessionInfo(@JsonProperty("WeekendInfo")val weekendInfo: WeekendInfo) {
}

data class WeekendInfo(@JsonProperty("TrackName")val trackName: String, @JsonProperty("TrackID")val trackId: Int) {

}
