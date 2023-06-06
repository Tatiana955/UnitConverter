package com.example.unitconverter.data.repository

import com.example.unitconverter.domain.local.LocalModel
import com.example.unitconverter.domain.repository.Repository
import com.example.unitconverter.domain.util.Type

class RepositoryImpl(private val local: LocalModel): Repository {

    override fun conversion(
        fromUnit: String,
        toUnit: String,
        type: String,
        num: Double
    ): Double {
        return when(type) {
            Type.WEIGHT.value, Type.LENGTH.value ->
                local.conversion(fromUnit, toUnit, num)
            Type.TEMPERATURE.value ->
                local.temperatureConversion(fromUnit, toUnit, num)
            else -> 0.0
        }
    }
}