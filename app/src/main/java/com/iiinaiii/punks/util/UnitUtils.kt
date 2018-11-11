package com.iiinaiii.punks.util

enum class ValueUnit(val fullName: String, val displayName: String) {
    LITERS("liters", "L"),
    GRAMS("grams", "g"),
    KILOGRAMS("kilograms", "kg");

    companion object {
        fun convertToDispName(fullName: String): String {
            return values().find { it.fullName == fullName }?.displayName ?: fullName
        }
    }
}