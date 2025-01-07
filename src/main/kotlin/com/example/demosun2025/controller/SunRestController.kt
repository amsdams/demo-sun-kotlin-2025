package com.example.demosun2025.controller

import com.example.demosun2025.model.Interval
import com.example.demosun2025.model.Location
import com.example.demosun2025.model.Sun
import com.example.demosun2025.service.SunService
import net.time4j.PlainDate
import net.time4j.tz.TZID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping(value = ["suns"])
class SunRestController(private val sunService: SunService) {

    @GetMapping(value = ["/today"])
    fun today(
        @RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") tz: String?,
        @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") location: Location
    ): Sun {
        val id = TZID { tz }
        val now: LocalDate = LocalDate.now()

        return getSun(location, id, now)
    }

    @GetMapping(value = ["/future"])
    fun future(
        @RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") tz: String?,
        @RequestParam(value = "amount", defaultValue = "7") amount: Int,
        @RequestParam(value = "interval", defaultValue = "0") interval: Interval,
        @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") location: Location
    ): List<Sun> {
        val id = TZID { tz }
        val suns: MutableList<Sun> = ArrayList()

        when (interval) {
            Interval.DAY -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now().plusDays(i.toLong())

                    val sun = getSun(location, id, now)
                    suns.add(sun)
                    ++i
                }
            }

            Interval.MONTH -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now().plusDays(i.toLong())

                    val sun = getSun(location, id, now)
                    suns.add(sun)
                    ++i
                }
            }

            Interval.YEAR -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now().plusDays(i.toLong())

                    val sun = getSun(location, id, now)
                    suns.add(sun)
                    ++i
                }
            }
        }

        return suns
    }

    private fun getSun(location: Location, id: TZID, now: LocalDate): Sun {
        val plainDate: PlainDate = PlainDate.from(now)
        return Sun(
            now, sunService.getSunrise(id, plainDate, location),
            sunService.getSunSet(id, plainDate, location), id, location
        )
    }

    @GetMapping(value = ["/past"])
    fun past(
        @RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") tz: String?,
        @RequestParam(value = "amount", defaultValue = "7") amount: Int,
        @RequestParam(value = "interval", defaultValue = "0") interval: Interval,
        @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") location: Location
    ): List<Sun> {
        val id = TZID { tz }
        val suns: MutableList<Sun> = ArrayList()

        when (interval) {
            Interval.DAY -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now().minusDays(i.toLong())

                    val sun = getSun(location, id, now)
                    suns.add(sun)
                    ++i
                }
            }

            Interval.MONTH -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now().minusDays(i.toLong())

                    val sun = getSun(location, id, now)
                    suns.add(sun)
                    ++i
                }
            }

            Interval.YEAR -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now().minusDays(i.toLong())

                    val sun = getSun(location, id, now)
                    suns.add(sun)
                    ++i
                }
            }

        }

        return suns
    }
}
