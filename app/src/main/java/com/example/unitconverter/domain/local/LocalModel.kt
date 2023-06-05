package com.example.unitconverter.domain.local

interface LocalModel {

    fun conversion(fromUnit: String, toUnit: String, num: Double): Double

    fun temperatureConversion(fromUnit: String, toUnit: String, num: Double): Double
}