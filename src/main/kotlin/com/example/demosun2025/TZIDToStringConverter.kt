package com.example.demosun2025

import net.time4j.tz.TZID
import org.springframework.core.convert.converter.Converter

class TZIDToStringConverter : Converter<TZID, String> {
    override fun convert(tzid: TZID): String {
        return tzid.canonical()
    }
}