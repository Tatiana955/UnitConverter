package com.example.unitconverter.data.local

import com.example.unitconverter.domain.local.LocalModel
import com.example.unitconverter.domain.util.Units

class LocalModelImpl: LocalModel {

    override fun conversion(
        fromUnit: String,
        toUnit: String,
        num: Double
    ): Double {
        return num * Units.valueOf(fromUnit).unit.transform / Units.valueOf(toUnit).unit.transform
    }

    override fun temperatureConversion(
        fromUnit: String,
        toUnit: String,
        num: Double,
    ): Double {
        return when (Units.valueOf(fromUnit)) {
            Units.Celsius -> {
                when (Units.valueOf(toUnit)) {
                    Units.Fahrenheit -> celsiusToFahrenheit(num)
                    Units.Kelvins -> celsiusToKelvins(num)
                    else -> num
                }
            }
            Units.Fahrenheit -> {
                when (Units.valueOf(toUnit)) {
                    Units.Celsius -> fahrenheitToCelsius(num)
                    Units.Kelvins -> fahrenheitToKelvins(num)
                    else -> num
                }
            }
            Units.Kelvins -> {
                when (Units.valueOf(toUnit)) {
                    Units.Celsius -> kelvinsToCelsius(num)
                    Units.Fahrenheit -> kelvinsToFahrenheit(num)
                    else -> num
                }
            }
            else -> 0.0
        }
    }

    private fun celsiusToFahrenheit(c: Double) = c * 9 / 5 + FREEZING_POINT_F

    private fun fahrenheitToCelsius(f: Double) = (f - FREEZING_POINT_F) * 5 / 9

    private fun kelvinsToCelsius(k: Double) = k - ABSOLUTE_ZERO_C

    private fun celsiusToKelvins(c: Double) = c + ABSOLUTE_ZERO_C

    private fun fahrenheitToKelvins(f: Double) = (f + ABSOLUTE_ZERO_F) * 5 / 9

    private fun kelvinsToFahrenheit(k: Double) =  k * 9 / 5 - ABSOLUTE_ZERO_F

    private companion object Constants {
        const val ABSOLUTE_ZERO_C = 273.15
        const val ABSOLUTE_ZERO_F = 459.67
        const val FREEZING_POINT_F = 32
    }
}