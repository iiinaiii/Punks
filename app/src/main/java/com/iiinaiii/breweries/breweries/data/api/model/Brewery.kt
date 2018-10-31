package com.iiinaiii.breweries.breweries.data.api.model

data class Brewery(
    val id: Int,
    val name: String,
    val brewery_type: String,
    val street: String,
    val city: String,
    val state: String
)