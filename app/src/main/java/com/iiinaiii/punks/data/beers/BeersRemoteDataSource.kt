package com.iiinaiii.punks.data.beers

import com.iiinaiii.punks.data.Result
import com.iiinaiii.punks.data.api.BeersSearchService
import com.iiinaiii.punks.data.beers.model.BeerResponse
import com.iiinaiii.punks.util.safeApiCall
import java.io.IOException
import javax.inject.Inject

class BeersRemoteDataSource @Inject constructor(private val service: BeersSearchService) {

    suspend fun search(
        page: Int
    ): Result<List<BeerResponse>> = safeApiCall(
        call = { requestSearch(page) },
        errorMessage = "Error getting Breweries data"
    )

    private suspend fun requestSearch(
        page: Int
    ): Result<List<BeerResponse>> {
        Result
        val response = service.search(page).await()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null && body.isNotEmpty()) {
                return Result.Success(body)
            }
        }
        return Result.Error(
            IOException("Error getting BeerResponse data ${response.code()} ${response.message()}")
        )
    }

    companion object {
        @Volatile
        private var INSTANCE: BeersRemoteDataSource? = null

        fun getInstance(service: BeersSearchService): BeersRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: BeersRemoteDataSource(service).also { INSTANCE = it }
            }
        }
    }
}