package com.example.demosun2025.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import net.time4j.tz.TZID
import java.time.LocalDate
import java.time.LocalDateTime


data class Moon(
    val date: LocalDate? = null,
    val rise: LocalDateTime? = null,
    val set: LocalDateTime? = null,

    @JsonIgnore
    val tzid: TZID? = null,
    val location: Location? = null
) {


    @get:JsonProperty("timezone")
    val timezone: String
        get() = tzid!!.canonical()
}
