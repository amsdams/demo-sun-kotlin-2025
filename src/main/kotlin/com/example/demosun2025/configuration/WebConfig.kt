package com.example.demosun2025.configuration

import com.example.demosun2025.StringToIntervalConverter
import com.example.demosun2025.StringToLocationConverter
import com.example.demosun2025.TZIDToStringConverter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@org.springframework.context.annotation.Configuration
class WebConfig : WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(StringToLocationConverter())
        registry.addConverter(TZIDToStringConverter())
        registry.addConverter(StringToIntervalConverter())
    }
}
