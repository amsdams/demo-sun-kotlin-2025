package com.example.demosun2025.controller

import com.example.demosun2025.model.Interval
import com.example.demosun2025.model.Location
import com.example.demosun2025.service.MoonService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class MoonRestControllerMockMvcIntTest {
    @MockitoBean
    private val clock: Clock? = null

    @Autowired
    private val mockMvc: MockMvc? = null

    @MockitoBean
    private val moonService: MoonService? = null

    @BeforeEach
    fun beforeEach() {
        Mockito.`when`(clock!!.instant()).then { invocation: InvocationOnMock? -> DEFAULT_NOW }
        Mockito.`when`(clock.zone).then { invocationOnMock: InvocationOnMock? ->
            ZoneId.of(
                DEFAULT_TZ
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun today() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/today")).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content()
                    .string("{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}")
            )
    }

    @Test
    @Throws(Exception::class)
    fun future_amount_7() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/future").param("amount", DEFAULT_AMOUNT.toString()))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun future_interval_DAY() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/future").param("interval", DEFAULT_INTERVAL.toString()))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun future_location_Ams() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/future").param("location", DEFAULT_LOCATION.toString()))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun future_tz_Ams() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/future").param("tz", DEFAULT_TZ))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun future() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/future")).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun past() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/past")).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun past_amount_7() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/past").param("amount", DEFAULT_AMOUNT.toString()))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun past_interval_DAY() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/past").param("interval", DEFAULT_INTERVAL.toString()))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun past_location_Ams() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/past").param("location", DEFAULT_LOCATION.toString()))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun past_tz_Ams() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/moons/past").param("tz", DEFAULT_TZ))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    "[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"
                )
            )
    }

    companion object {
        val DEFAULT_LOCATION: Location = Location(52.379189, 4.899431)
        private const val DEFAULT_TZ = "Europe/Amsterdam"
        private const val DEFAULT_AMOUNT = 7
        private val DEFAULT_INTERVAL = Interval.DAY
        private val DEFAULT_NOW: Instant = Instant.parse("2042-01-01T12:15:00Z")
    }
}