package com.example.demosun2025.controller

import com.example.demosun2025.model.Interval
import com.example.demosun2025.model.Location
import com.example.demosun2025.model.Moon
import com.example.demosun2025.service.MoonService
import net.time4j.PlainDate
import net.time4j.tz.TZID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Clock
import java.time.LocalDate

@RestController
@RequestMapping(value = ["moons"])
class MoonRestController(moonService: MoonService, clock: Clock) {
    private val moonService: MoonService = moonService
    private val clock: Clock = clock

    @GetMapping(value = ["/today"])
    fun today(
        @RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") tz: String?,
        @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") location: Location
    ): Moon {
        val id: TZID = TZID { tz }
        val now: LocalDate = LocalDate.now(clock)

        return getMoon(location, id, now)
    }

    @GetMapping(value = ["/future"])
    fun future(
        @RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") tz: String?,
        @RequestParam(value = "amount", defaultValue = "7") amount: Int,
        @RequestParam(value = "interval", defaultValue = "DAY") interval: Interval,
        @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") location: Location
    ): List<Moon> {
        val id: TZID = TZID { tz }
        val moons: MutableList<Moon> = ArrayList<Moon>()

        when (interval) {
            Interval.DAY -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now(clock).plusDays(i.toLong())
                    val moon: Moon = getMoon(location, id, now)

                    moons.add(moon)
                    ++i
                }
            }

            Interval.MONTH -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now(clock).plusMonths(i.toLong())
                    val moon: Moon = getMoon(location, id, now)

                    moons.add(moon)
                    ++i
                }
            }

            Interval.YEAR -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now(clock).plusYears(i.toLong())

                    val moon: Moon = getMoon(location, id, now)
                    moons.add(moon)
                    ++i
                }
            }

            else -> {}
        }

        return moons
    }

    @GetMapping(value = ["/past"])
    fun past(
        @RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") tz: String?,
        @RequestParam(value = "amount", defaultValue = "7") amount: Int,
        @RequestParam(value = "interval", defaultValue = "DAY") interval: Interval,
        @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") location: Location
    ): List<Moon> {
        val id: TZID = TZID { tz }
        val moons: MutableList<Moon> = ArrayList<Moon>()

        when (interval) {
            Interval.DAY -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now(clock).minusDays(i.toLong())

                    val moon: Moon = getMoon(location, id, now)
                    moons.add(moon)
                    ++i
                }
            }

            Interval.MONTH -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now(clock).minusMonths(i.toLong())

                    val moon: Moon = getMoon(location, id, now)
                    moons.add(moon)
                    ++i
                }
            }

            Interval.YEAR -> {
                var i = 0
                while (i < amount) {
                    val now: LocalDate = LocalDate.now(clock).minusYears(i.toLong())
                    val moon: Moon = getMoon(location, id, now)

                    moons.add(moon)
                    ++i
                }
            }

            else -> {}
        }

        return moons
    }

    private fun getMoon(location: Location, id: TZID, now: LocalDate): Moon {
        val plainDate: PlainDate = PlainDate.from(now)
        return Moon(
            now, moonService.getMoonrise(id, plainDate, location),
            moonService.getMoonSet(id, plainDate, location), id, location
        )
    }
}
