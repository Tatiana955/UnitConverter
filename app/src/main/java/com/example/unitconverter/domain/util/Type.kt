package com.example.unitconverter.domain.util

import com.example.unitconverter.R

enum class Type(val value: String, val icon: Int) {
    LENGTH("Length", R.drawable.straighten_48px),
    WEIGHT("Weight", R.drawable.weight_48px),
    TEMPERATURE("Temperature", R.drawable.thermometer_48px)
}