package com.example.demosun2025.controller

import net.time4j.tz.TZID
import net.time4j.tz.Timezone
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping(value = ["util"])
class UtilRestController {
    @GetMapping(value = ["/timezones"])
    fun timezones(): List<String> {
        val ids: List<TZID> = Timezone.getAvailableIDs()
        return ids.stream().map(TZID::canonical).collect(Collectors.toList())
    }
}
