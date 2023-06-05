package com.example.unitconverter.data.models

/**
 * @param transform use meters for length and grams for weight
 */
data class Unit(
    val transform: Double,
    val short: String,
    val singular: String,
    val plural: String,
)