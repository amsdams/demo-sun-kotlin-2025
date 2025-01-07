package com.example.demosun2025.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UtilRestControllerIntTest {
    @Autowired
    private val utilRestController: UtilRestController? = null

    @Test
    fun timezones() {
        val timezones = utilRestController!!.timezones()
        Assertions.assertNotNull(timezones)
    }
}