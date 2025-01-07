package com.example.demosun2025

import com.example.demosun2025.model.Location
import org.springframework.core.convert.converter.Converter

class StringToLocationConverter : Converter<String, Location> {
    override fun convert(source: String): Location {
        val l = Location()
        val latlong = source.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        l.latitude = (latlong[0].toDouble())
        l.longitude = (latlong[1].toDouble())

        return l
    }
}