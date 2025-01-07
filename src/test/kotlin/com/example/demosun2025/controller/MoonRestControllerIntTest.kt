package com.example.demosun2025.controller

import com.example.demosun2025.model.Interval
import com.example.demosun2025.model.Location
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@SpringBootTest
internal class MoonRestControllerIntTest {
    @Autowired
    private val moonRestController: MoonRestController? = null

    @Test
    fun today() {
        val moon = moonRestController!!.today(DEFAULT_TZ, DEFAULT_LOCATION)
        Assertions.assertNotNull(moon)
    }

    @Test
    fun future() {
        val moons = moonRestController!!.future(DEFAULT_TZ, DEFAULT_AMOUNT, DEFAULT_INTERVAL, DEFAULT_LOCATION)
        Assertions.assertNotNull(moons)
        Assertions.assertTrue(moons.isNotEmpty())
        Assertions.assertTrue(moons.size == DEFAULT_AMOUNT)
    }

    @ParameterizedTest(name = "{0} + {1} + {2} +{3}")
    @MethodSource("getData")
    fun futureParameterized(tz: String, amount: Int, interval: Interval, location: Location) {
        val moons = moonRestController!!.future(tz, amount, interval, location)
        Assertions.assertNotNull(moons)
        Assertions.assertTrue(moons.isNotEmpty())
        Assertions.assertEquals(moons.size, amount)
        val firstMoon = moons[0]
        Assertions.assertEquals(
            firstMoon.date.toString(),
            LocalDate.now().plus(0, ChronoUnit.valueOf(interval.toString() + "S")).toString()
        )

        //Assertions.assertEquals(firstMoon.rise.toString(), "2025-01-07T11:58:07")
        //Assertions.assertEquals(firstMoon.set.toString(), "2025-01-07T01:15:33")
        Assertions.assertEquals(firstMoon.location.toString(), location.toString())
        Assertions.assertEquals(firstMoon.timezone, tz)

        println(moons[moons.size - 1].timezone)

        val lastMoon = moons[moons.size - 1]
        Assertions.assertEquals(
            lastMoon.date.toString(),
            LocalDate.now().plus(amount.toLong() - 1, ChronoUnit.valueOf(interval.toString() + "S")).toString()
        )
        //Assertions.assertEquals(lastMoon.rise.toString(), "2029-01-07T00:45:34")
        //Assertions.assertEquals(lastMoon.set.toString(), "2029-01-07T11:21:26")
        Assertions.assertEquals(firstMoon.location.toString(), location.toString())
        Assertions.assertEquals(firstMoon.timezone, tz)

    }


    @Test
    fun past() {
        val moons = moonRestController!!.past(DEFAULT_TZ, DEFAULT_AMOUNT, DEFAULT_INTERVAL, DEFAULT_LOCATION)
        Assertions.assertNotNull(moons)
    }

    companion object {
        val DEFAULT_LOCATION: Location = Location(52.379189, 4.899431)
        private const val DEFAULT_TZ = "Europe/Amsterdam"
        private const val DEFAULT_AMOUNT = 7
        private val DEFAULT_INTERVAL = Interval.DAY

        @JvmStatic
        fun getData(): List<Arguments> {
            return listOf(
                Arguments.of(
                    "Europe/Amsterdam", 7, Interval.DAY, DEFAULT_LOCATION,
                ),
                Arguments.of(
                    "Europe/Berlin", 30, Interval.MONTH, DEFAULT_LOCATION,
                ),
                Arguments.of(
                    "Europe/Paris", 5, Interval.YEAR, DEFAULT_LOCATION,
                )
            )
        }
    }
}
