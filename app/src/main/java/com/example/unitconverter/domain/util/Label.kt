package com.example.unitconverter.domain.util

import com.example.unitconverter.R

enum class Label(val text: String, val icon: Int) {
    Settings("Settings", R.drawable.baseline_settings_24),
    EnterNumber("Enter your number", R.drawable.baseline_edit_24),
    Result("Result", R.drawable.baseline_calculate_24)
}