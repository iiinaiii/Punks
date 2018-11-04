package com.iiinaiii.punks.data.beers

import com.iiinaiii.punks.data.Result
import com.iiinaiii.punks.data.beers.model.BeerResponse

class BeersRepository constructor(private val remoteDataSource: BeersRemoteDataSource) {

    private val breweryCache = mutableMapOf<Int, BeerResponse>()

    suspend fun search(
        page: Int
    ): Result<List<BeerResponse>> {
        val result = remoteDataSource.search(page)
        if (result is Result.Success) {
            cache(result.data)
        }
        return result
    }

    private fun cache(data: List<BeerResponse>) {
        data.associateTo(breweryCache) { it.id to it }
    }


    companion object {
        @Volatile
        private var INSTANCE: BeersRepository? = null

        fun getInstance(
            remoteDataSource: BeersRemoteDataSource
        ): BeersRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: BeersRepository(remoteDataSource).also { INSTANCE = it }
            }
        }
    }
}