package com.iiinaiii.punks.util.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter


class LocalDateTimeAdapter {
    companion object {
        private val FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy")

        @ToJson
        fun toJson(value: YearMonth): String {
            return value.format(FORMATTER)
        }

        @FromJson
        fun fromJson(value: String): YearMonth {
            return YearMonth.parse(value, FORMATTER)
        }

    }
}