package com.iiinaiii.punks.punkapi.data.search

import com.iiinaiii.punks.punkapi.data.api.model.Beer
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersSearchService {

    @GET("")
    fun search(
        @Query("page") page: Int
    ): Deferred<Response<List<Beer>>>

    companion object {
        const val ENDPOINT = "https://api.punkapi.com/v2/"
        const val PER_PAGE_DEFAULT = 12
    }
}