package com.example.demosun2025.controller

import com.example.demosun2025.model.Interval
import com.example.demosun2025.model.Location
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

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
    }
}