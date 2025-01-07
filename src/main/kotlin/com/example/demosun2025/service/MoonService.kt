package com.example.demosun2025.service

import net.time4j.Moment
import net.time4j.PlainDate
import net.time4j.PlainTimestamp
import net.time4j.TemporalType
import net.time4j.calendar.astro.LunarTime
import net.time4j.tz.TZID
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MoonService {
    fun getMoonrise(id: TZID, plainDate: PlainDate, location: com.example.demosun2025.model.Location): LocalDateTime? {
        val amsterdam: LunarTime = LunarTime.ofLocation(id, location.latitude!!, location.longitude!!)

        val moonlight: LunarTime.Moonlight = amsterdam.on(plainDate)
        val moonrise: java.util.Optional<Moment> = moonlight.moonrise()

        if (moonrise.isPresent) {
            val moonrisePlainTimestamp: PlainTimestamp = moonrise.get().toZonalTimestamp(id.canonical())
            return TemporalType.LOCAL_DATE_TIME.from(moonrisePlainTimestamp)
        }
        return null
    }

    fun getMoonSet(id: TZID, plainDate: PlainDate, location: com.example.demosun2025.model.Location): LocalDateTime? {
        val amsterdam: LunarTime = LunarTime.ofLocation(id, location.latitude!!, location.longitude!!)

        val moonlight: LunarTime.Moonlight = amsterdam.on(plainDate)
        val moonset: java.util.Optional<Moment> = moonlight.moonset()

        if (moonset.isPresent) {
            val moonsetPlainTimestamp: PlainTimestamp = moonset.get().toZonalTimestamp(id.canonical())
            return TemporalType.LOCAL_DATE_TIME.from(moonsetPlainTimestamp)
        }
        return null
    }
}
