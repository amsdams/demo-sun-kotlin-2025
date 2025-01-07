package com.example.demosun2025.model


data class Location(
    var latitude: Double? = null,
    var longitude: Double? = null
) {


    override fun toString(): String {
        return latitude.toString() +
                ", " + longitude
    }
}
