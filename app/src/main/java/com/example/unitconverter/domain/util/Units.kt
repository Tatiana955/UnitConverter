package com.example.unitconverter.domain.util

import com.example.unitconverter.data.models.Unit

enum class Units(val type: Type, val unit: Unit) {
    // Length
    Meters(Type.LENGTH, Unit(1.0, "m", "meter", "meters")),
    Kilometers(Type.LENGTH, Unit(1000.0, "km", "kilometer", "kilometers")),
    Centimeters(Type.LENGTH, Unit(0.01, "cm", "centimeter", "centimeters")),
    Millimeters(Type.LENGTH, Unit(0.001, "mm", "millimeter", "millimeters")),
    Miles(Type.LENGTH, Unit(1609.35, "mi", "mile", "miles")),
    Yards(Type.LENGTH, Unit(0.9144, "yd", "yard", "yards")),
    Feet(Type.LENGTH, Unit(0.3048, "ft", "foot", "feet")),
    Inches(Type.LENGTH, Unit(0.0254, "in", "inch", "Inches")),

    // Weight
    Grams(Type.WEIGHT, Unit(1.0, "g", "gram", "grams", )),
    Kilograms(Type.WEIGHT, Unit(1000.0, "kg", "kilogram", "kilograms")),
    Milligrams(Type.WEIGHT, Unit(0.001, "mg", "milligram", "milligrams")),
    Pounds(Type.WEIGHT, Unit(453.592, "lb", "pound", "pounds")),
    Ounces(Type.WEIGHT, Unit(28.3495, "oz", "ounce", "ounces")),

    // Temperature
    Celsius(Type.TEMPERATURE, Unit(0.0, "c", "degree celsius", "degrees celsius")),
    Fahrenheit(Type.TEMPERATURE, Unit(0.0, "f", "degree fahrenheit", "degrees fahrenheit")),
    Kelvins(Type.TEMPERATURE, Unit(0.0, "k", "kelvin", "kelvins"))
}