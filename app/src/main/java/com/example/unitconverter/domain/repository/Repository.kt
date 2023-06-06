package com.example.unitconverter.domain.repository

interface Repository {

    fun conversion(fromUnit: String, toUnit: String, type: String, num: Double): Double
}