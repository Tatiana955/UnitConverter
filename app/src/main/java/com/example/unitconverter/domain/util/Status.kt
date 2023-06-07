package com.example.unitconverter.domain.util

enum class Status(val status: String, val color: SpecialColor) {
    Success("Success", SpecialColor.CORRECT),
    Negative("%INPUT% shouldn't be negative.", SpecialColor.WRONG),
    WrongType("Wrong type selected.", SpecialColor.WRONG),
    EmptyUnits("Units can't be empty.", SpecialColor.WRONG),
    EmptyNumber("Number can't be empty.", SpecialColor.WRONG),
    ImpossibleConversion("Conversion from %INPUT% to %INPUT1% is impossible.", SpecialColor.WRONG);

    fun getStatus(
        input: String,
        input1: String = ""
    ) = status.replace("%INPUT%", input).replace("%INPUT1%", input1)
}