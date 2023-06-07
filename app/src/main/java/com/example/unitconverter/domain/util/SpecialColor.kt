package com.example.unitconverter.domain.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

enum class SpecialColor {
    CORRECT,
    WRONG;

    val color: Color
        @Composable
        @ReadOnlyComposable
        get() = when(this) {
            CORRECT -> MaterialTheme.colorScheme.onBackground
            WRONG -> MaterialTheme.colorScheme.error
        }
}