package com.iiinaiii.punks.domain.model

import com.iiinaiii.punks.ui.home.BeerItemUiModel

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
    val abv: String
)

fun Beer.toItemUiModel() = BeerItemUiModel(
    name = name,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)