package com.iiinaiii.punks.data.beers.model

import com.iiinaiii.punks.domain.model.Beer
import org.threeten.bp.YearMonth
import org.threeten.bp.format.TextStyle
import java.util.*

data class BeerResponse(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: YearMonth,
    val description: String,
    val image_url: String,
    val abv: Float?,
    val ibu: Float?,
    val target_fg: Float?,
    val target_og: Float?,
    val ebc: Float?,
    val srm: Float?,
    val volume: VolumeResponse,
    val boil_volume: VolumeResponse,
    val ph: Float?,
    val attenuation_level: Float?,
    val food_pairing: List<String>,
    val brewers_tips: String
)

fun BeerResponse.toBeer() = Beer(
    id = id,
    name = name,
    tagline = tagline,
    firstBrewed = first_brewed.run { "${month.getDisplayName(TextStyle.FULL, Locale.US).toUpperCase()} $year" },
    description = description,
    imageUrl = image_url,
    abv = "${abv.toStringOrDefault()}%",
    ibu = ibu.toStringOrDefault(),
    targetOg = target_og.toStringOrDefault(),
    targetFg = target_fg.toStringOrDefault(),
    ebc = ebc.toStringOrDefault(),
    srm = srm.toStringOrDefault(),
    volume = volume.toVolumeText(),
    boilVolume = boil_volume.toVolumeText(),
    ph = ph.toStringOrDefault(),
    attenuationLevel = attenuation_level.toStringOrDefault(),
    foodPairing = food_pairing,
    brewersTips = brewers_tips
)

private fun Float?.toStringOrDefault(): String {
    return this?.toString() ?: "-"
}
