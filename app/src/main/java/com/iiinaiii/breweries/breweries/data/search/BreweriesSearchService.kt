package com.iiinaiii.breweries.breweries.data.search

import android.graphics.pdf.PdfDocument
import com.iiinaiii.breweries.breweries.data.api.model.Brewery
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BreweriesSearchService {

    @GET("")
    fun searchByState(
        @Query("by_state") state: String,
        @Query("page") page: Int
    ): Deferred<Response<List<Brewery>>>

    @GET("")
    fun searchByType(
        @Query("by_type") type: String
    ): Deferred<Response<List<Brewery>>>

    companion object {
        const val ENDPOINT = "https://api.openbrewerydb.org/breweries"
        const val PER_PAGE_DEFAULT = 12
    }
}