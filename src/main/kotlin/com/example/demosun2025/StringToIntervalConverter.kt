package com.example.demosun2025

import com.example.demosun2025.model.Interval
import org.springframework.core.convert.converter.Converter

class StringToIntervalConverter : Converter<String?, Interval> {

    override fun convert(source: String): Interval {
        return Interval.valueOf(source)
    }
}