package com.example.unitconverter.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.unitconverter.domain.repository.Repository
import com.example.unitconverter.domain.util.SpecialColor
import com.example.unitconverter.domain.util.Status
import com.example.unitconverter.domain.util.Type
import com.example.unitconverter.domain.util.Units

class AppViewModel(private val repo: Repository): ViewModel() {

    val types: Array<Type> = Type.values()
    private val units: Array<Units> = Units.values()

    private val _typeState = mutableStateOf(types[0].value)
    val typeState: MutableState<String> = _typeState

    private val _sortUnits = mutableStateOf(
        units.filter { it.type.value == _typeState.value }
    )
    val sortUnits: MutableState<List<Units>> = _sortUnits

    fun saveType(type: String) {
        _typeState.value = type
        _sortUnits.value = units.filter {
            it.type.value == _typeState.value
        }
    }

    private val _fromUnitState = mutableStateOf("")
    val fromUnitState: MutableState<String> = _fromUnitState

    fun saveFromUnit(unit: String) {
        _fromUnitState.value = unit
    }

    private val _toUnitState = mutableStateOf("")
    val toUnitState: MutableState<String> = _toUnitState

    fun saveToUnit(unit: String) {
        _toUnitState.value = unit
    }

    private val _number = mutableStateOf(0.0)
    val number: MutableState<Double> = _number

    private var isEmptyNum = false

    fun saveNum(num: String) {
        try {
            _number.value = num.replace(",", ".").toDouble()
            isEmptyNum = false
        } catch (e: NumberFormatException) {
            isEmptyNum = true
        }
    }

    private val _result = mutableStateOf("")
    val result: MutableState<String> = _result

    fun getResult() {
        _result.value = converter()
    }

    private val _color = mutableStateOf(SpecialColor.CORRECT)
    val color: MutableState<SpecialColor> = _color

    private fun converter(): String {
        return when(check()) {
            Status.Success -> {
                _color.value = Status.Success.color
                repo.conversion(
                    fromUnit = _fromUnitState.value,
                    toUnit = _toUnitState.value,
                    type = _typeState.value,
                    num = _number.value
                ).toString()
            }

            Status.Negative -> {
                _color.value = Status.Negative.color
                Status.Negative.getStatus(
                    input = _typeState.value
                )
            }

            Status.WrongType -> {
                _color.value = Status.WrongType.color
                Status.WrongType.status
            }

            Status.EmptyUnits -> {
                _color.value = Status.EmptyUnits.color
                Status.EmptyUnits.status
            }

            Status.EmptyNumber -> {
                _color.value = Status.EmptyNumber.color
                Status.EmptyNumber.status
            }

            Status.ImpossibleConversion -> {
                _color.value = Status.ImpossibleConversion.color
                Status.ImpossibleConversion.getStatus(
                    input = Units.valueOf(_fromUnitState.value).unit.plural,
                    input1 = Units.valueOf(_toUnitState.value).unit.plural
                )
            }
        }
    }

    private fun check(): Status {
        if (_fromUnitState.value.isEmpty() || _toUnitState.value.isEmpty())
            return Status.EmptyUnits

        if (isEmptyNum)
            return Status.EmptyNumber

        if (_number.value < 0.0
            && (_typeState.value == Type.LENGTH.value || _typeState.value == Type.WEIGHT.value))
            return Status.Negative

        if (Units.valueOf(_fromUnitState.value).type.value !== _typeState.value
            && Units.valueOf(_toUnitState.value).type.value !== _typeState.value)
            return Status.WrongType

        if (Units.valueOf(_fromUnitState.value).type.value !== _typeState.value
            || Units.valueOf(_toUnitState.value).type.value !== _typeState.value)
            return Status.ImpossibleConversion

        return Status.Success
    }
}