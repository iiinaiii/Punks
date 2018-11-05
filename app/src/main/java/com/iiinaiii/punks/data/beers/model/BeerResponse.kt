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
    val abv: Float,
    val ibu: Float?,
    val target_fg: Float?,
    val target_og: Float?,
    val ebc: Float?,
    val srm: Float?
)

fun BeerResponse.toBeer() = Beer(
    id = id,
    name = name,
    tagline = tagline,
    firstBrewed = first_brewed.run { "${month.getDisplayName(TextStyle.FULL, Locale.US).toUpperCase()} $year" },
    description = description,
    imageUrl = image_url,
    abv = "$abv%"
)