package com.iiinaiii.punks.data.beers.model

import com.iiinaiii.punks.util.ValueUnit

data class VolumeResponse(
    val value: Int,
    val unit: String
)

fun VolumeResponse.toVolumeText(): String {
    return value.toString() + ValueUnit.convertToDispName(unit)
}