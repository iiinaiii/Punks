package com.iiinaiii.breweries.breweries.data

import com.iiinaiii.breweries.breweries.data.api.model.Brewery
import com.iiinaiii.breweries.breweries.data.search.SearchRemoteDataSource
import com.iiinaiii.breweries.data.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BreweriesRepository constructor(private val remoteDataSource: SearchRemoteDataSource) {

    private val breweryCache = mutableMapOf<Int, Brewery>()

    suspend fun search(
        state: String,
        page: Int
    ): Result<List<Brewery>> {
        val result = remoteDataSource.search(state, page)
        if (result is Result.Success) {
            cache(result.data)
        }
        return result
    }

    private fun cache(data: List<Brewery>) {
        data.associateTo(breweryCache) { it.id to it }
    }


    companion object {
        @Volatile
        private var INSTANCE: BreweriesRepository? = null

        fun getInstance(
            remoteDataSource: SearchRemoteDataSource
        ): BreweriesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BreweriesRepository(remoteDataSource).also { INSTANCE = it }
            }
        }
    }
}