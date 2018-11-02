package com.iiinaiii.punks.punkapi.data.search

import com.iiinaiii.punks.punkapi.data.Result
import com.iiinaiii.punks.punkapi.data.api.model.Beer
import com.iiinaiii.punks.util.safeApiCall
import java.io.IOException
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(private val service: BeersSearchService) {

    suspend fun search(
        page: Int
    ): Result<List<Beer>> = safeApiCall(
        call = { requestSearch(page) },
        errorMessage = "Error getting Breweries data"
    )

    private suspend fun requestSearch(
        page: Int
    ): Result<List<Beer>> {
        Result
        val response = service.search(page).await()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }
        return Result.Error(
            IOException("Error getting Beer data ${response.code()} ${response.message()}")
        )
    }

    companion object {
        @Volatile
        private var INSTANCE: SearchRemoteDataSource? = null

        fun getInstance(service: BeersSearchService): SearchRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SearchRemoteDataSource(service).also { INSTANCE = it }
            }
        }
    }
}