package com.iiinaiii.punks.punkapi.data

import com.iiinaiii.punks.punkapi.data.api.model.Beer
import com.iiinaiii.punks.punkapi.data.search.SearchRemoteDataSource

class BeersRepository constructor(private val remoteDataSource: SearchRemoteDataSource) {

    private val breweryCache = mutableMapOf<Int, Beer>()

    suspend fun search(
        page: Int
    ): Result<List<Beer>> {
        val result = remoteDataSource.search(page)
        if (result is Result.Success) {
            cache(result.data)
        }
        return result
    }

    private fun cache(data: List<Beer>) {
        data.associateTo(breweryCache) { it.id to it }
    }


    companion object {
        @Volatile
        private var INSTANCE: BeersRepository? = null

        fun getInstance(
            remoteDataSource: SearchRemoteDataSource
        ): BeersRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BeersRepository(remoteDataSource).also { INSTANCE = it }
            }
        }
    }
}