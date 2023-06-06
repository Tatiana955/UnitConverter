package com.example.unitconverter.domain.util

enum class Status(val status: String) {
    Success("Success"),
    Negative("%INPUT% shouldn't be negative."),
    WrongType("Wrong type selected."),
    EmptyUnits("Units can't be empty."),
    EmptyNumber("Number can't be empty."),
    ImpossibleConversion("Conversion from %INPUT% to %INPUT1% is impossible.");

    fun getStatus(
        input: String,
        input1: String = ""
    ) = status.replace("%INPUT%", input).replace("%INPUT1%", input1)
}