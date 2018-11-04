package com.iiinaiii.punks.data.api

import com.iiinaiii.punks.data.beers.model.BeerResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersSearchService {

    @GET("beers")
    fun search(
        @Query("page") page: Int
    ): Deferred<Response<List<BeerResponse>>>

    companion object {
        const val ENDPOINT = "https://api.punkapi.com/v2/"
    }
}