package com.example.unitconverter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.unitconverter.R
import com.example.unitconverter.domain.util.Label
import com.example.unitconverter.domain.util.Type
import com.example.unitconverter.ui.screens.components.ExposedDropdownMenu
import com.example.unitconverter.ui.screens.components.LabelItem

@Composable
fun AppScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Content(
                    viewModel = viewModel,
                    modifier = modifier
                )
            }
        }
    )
}

@Composable
private fun Content(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            LabelItem(
                modifier = modifier,
                label = Label.Settings.text,
                id = Label.Settings.icon
            )
            TypeMenu(
                viewModel = viewModel,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(10.dp))
            FromUnitMenu(
                viewModel = viewModel,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(10.dp))
            ToUnitMenu(
                viewModel = viewModel,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(10.dp))
            LabelItem(
                modifier = modifier,
                label = Label.EnterNumber.text,
                id = Label.EnterNumber.icon
            )
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                UnitIcon(viewModel = viewModel)
            }
            Spacer(modifier = modifier.height(10.dp))
            TextFieldNum(
                viewModel = viewModel,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(10.dp))
            LabelItem(
                modifier = modifier,
                label = Label.Result.text,
                id = Label.Result.icon
            )
            ShowResult(
                viewModel = viewModel,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(10.dp))
        }
        FloatingActionButton(
            viewModel = viewModel,
            modifier = modifier
                .padding(all = 16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun TypeMenu(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    val types = viewModel.types.map { it.value }
    val selectedType by remember { viewModel.typeState }
    ExposedDropdownMenu(
        modifier = modifier,
        value = selectedType,
        label = stringResource(
            id = R.string.type
        ),
        items = types,
        save = { viewModel.saveType(it) },
    )
}

@Composable
private fun FromUnitMenu(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    val units = viewModel.sortUnits.value.map { it.name }
    val selectedUnit by remember { viewModel.fromUnitState }
    ExposedDropdownMenu(
        modifier = modifier,
        value = selectedUnit,
        label = stringResource(
            id = R.string.convert_from_unit
        ),
        items = units,
        save = { viewModel.saveFromUnit(it) }
    )
}

@Composable
private fun ToUnitMenu(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    val units = viewModel.sortUnits.value.map { it.name }
    val selectedUnit by remember { viewModel.toUnitState }
    ExposedDropdownMenu(
        modifier = modifier,
        value = selectedUnit,
        label = stringResource(
            id = R.string.convert_to_unit
        ),
        items = units,
        save = { viewModel.saveToUnit(it) }
    )
}

@Composable
private fun UnitIcon(
    viewModel: AppViewModel
) {
    val selectedType by remember { viewModel.typeState }
    Image(
        painter = painterResource(
            Type.valueOf(selectedType.uppercase()).icon
        ),
        contentDescription = stringResource(
            id = R.string.unit_icon
        ),
        colorFilter = ColorFilter
            .tint(MaterialTheme.colorScheme.primary)
    )
}

@Composable
private fun TextFieldNum(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    var value by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
            viewModel.saveNum(value)
        },
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = stringResource(
                    id = R.string.number
                )
            )
        },
        placeholder = {
            Text(
                text = stringResource(
                    id = R.string.enter_your_number
                )
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    value = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(
                        id = R.string.clear_icon
                    )
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                viewModel.saveNum(value)
                viewModel.getResult()
                focusManager.clearFocus()
            }
        )
    )
}

@Composable
private fun ShowResult(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    val result by remember { viewModel.result }
    val color = viewModel.color.value.color
    OutlinedTextField(
        value = result,
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth(),
        readOnly = true,
        textStyle = TextStyle(
            color = color
        )
    )
}

@Composable
private fun FloatingActionButton(
    viewModel: AppViewModel,
    modifier: Modifier
) {
    val value by remember { viewModel.number }
    val focusManager = LocalFocusManager.current
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = stringResource(
                    id = R.string.calculate
                )
            )
        },
        icon = {
            Icon(
                painter = painterResource(
                    R.drawable.baseline_calculate_24
                ),
                contentDescription = stringResource(
                    id = R.string.calculate_icon
                )
            )
        },
        onClick = {
            viewModel.saveNum(value.toString())
            viewModel.getResult()
            focusManager.clearFocus()
        },
        modifier = modifier
    )
}