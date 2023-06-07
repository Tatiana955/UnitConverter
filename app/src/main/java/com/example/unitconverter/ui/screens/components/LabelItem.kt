package com.example.unitconverter.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LabelItem(
    modifier: Modifier,
    label: String,
    id: Int,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = id),
            tint = color,
            contentDescription = null
        )
        Text(
            modifier = modifier
                .padding(start = 6.dp),
            text = label
        )
    }
    Spacer(modifier = modifier.height(10.dp))
}