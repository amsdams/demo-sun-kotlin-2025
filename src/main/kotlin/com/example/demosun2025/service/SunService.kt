package com.example.demosun2025.service

import net.time4j.Moment
import net.time4j.PlainDate
import net.time4j.PlainTimestamp
import net.time4j.TemporalType
import net.time4j.calendar.astro.SolarTime
import net.time4j.tz.TZID
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SunService {
    fun getSunrise(id: TZID, plainDate: PlainDate, location: com.example.demosun2025.model.Location): LocalDateTime? {
        val amsterdam: SolarTime = SolarTime.ofLocation(location.latitude!!, location.longitude!!)
        val sunrise: java.util.Optional<Moment> = plainDate.get(amsterdam.sunrise())
        if (sunrise.isPresent) {
            val sunrisePlainTimestamp: PlainTimestamp = sunrise.get().toZonalTimestamp(id.canonical())
            return TemporalType.LOCAL_DATE_TIME.from(sunrisePlainTimestamp)
        }
        return null
    }

    fun getSunSet(id: TZID, plainDate: PlainDate, location: com.example.demosun2025.model.Location): LocalDateTime? {
        val amsterdam: SolarTime = SolarTime.ofLocation(location.latitude!!, location.longitude!!)
        val sunset: java.util.Optional<Moment> = plainDate.get(amsterdam.sunset())
        if (sunset.isPresent) {
            val sunsetPlainTimestamp: PlainTimestamp = sunset.get().toZonalTimestamp(id.canonical())
            return TemporalType.LOCAL_DATE_TIME.from(sunsetPlainTimestamp)
        }
        return null
    }
}
