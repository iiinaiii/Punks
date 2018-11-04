package com.iiinaiii.punks.data.beers.model

import com.iiinaiii.punks.domain.model.Beer

data class BeerResponse(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,
    val image_url: String
)

fun BeerResponse.toBeer() = Beer(
    id = id,
    name = name,
    tagline = tagline,
    first_brewed = first_brewed,
    description = description,
    image_url = image_url
)