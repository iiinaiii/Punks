package com.iiinaiii.punks.data.beers

import com.iiinaiii.punks.data.Result
import com.iiinaiii.punks.data.beers.model.BeerResponse

class BeersRepository(private val remoteDataSource: BeersRemoteDataSource) {

    private val beerCache = mutableMapOf<Int, BeerResponse>()

    suspend fun search(
        page: Int
    ): Result<List<BeerResponse>> {
        val result = remoteDataSource.search(page)
        if (result is Result.Success) {
            cache(result.data)
        }
        return result
    }

    suspend fun getBeer(
        id: Int
    ): Result<BeerResponse> {
        val beer = beerCache[id]
        return if (beer != null) {
            Result.Success(beer)
        } else {
            Result.Error(IllegalStateException("Beer $id not cached"))
        }
    }

    private fun cache(data: List<BeerResponse>) {
        data.associateTo(beerCache) { it.id to it }
    }

    companion object {
        @Volatile
        private var INSTANCE: BeersRepository? = null

        fun getInstance(
            remoteDataSource: BeersRemoteDataSource
        ): BeersRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BeersRepository(remoteDataSource).also { INSTANCE = it }
            }
        }
    }
}