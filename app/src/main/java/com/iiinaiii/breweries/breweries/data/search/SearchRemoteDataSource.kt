package com.iiinaiii.breweries.breweries.data.search

import com.iiinaiii.breweries.breweries.data.Result
import com.iiinaiii.breweries.breweries.data.api.model.Brewery
import com.iiinaiii.breweries.util.safeApiCall
import java.io.IOException
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(private val service: BreweriesSearchService) {

    suspend fun search(
        state: String,
        page: Int
    ): Result<List<Brewery>> = safeApiCall(
        call = { requestSearch(state, page) },
        errorMessage = "Error getting Breweries data"
    )

    private suspend fun requestSearch(
        state: String,
        page: Int
    ): Result<List<Brewery>> {
        Result
        val response = service.searchByState(state, page).await()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }
        return Result.Error(
            IOException("Error getting Brewery data ${response.code()} ${response.message()}")
        )
    }

    companion object {
        @Volatile
        private var INSTANCE: SearchRemoteDataSource? = null

        fun getInstance(service: BreweriesSearchService): SearchRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SearchRemoteDataSource(service).also { INSTANCE = it }
            }
        }
    }
}