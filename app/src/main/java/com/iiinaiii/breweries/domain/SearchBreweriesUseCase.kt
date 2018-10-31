package com.iiinaiii.breweries.domain

import com.iiinaiii.breweries.breweries.data.BreweriesRepository
import com.iiinaiii.breweries.breweries.data.Result
import com.iiinaiii.breweries.breweries.data.api.model.Brewery
import com.iiinaiii.breweries.data.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchBreweriesUseCase @Inject constructor(
    private val breweriesRepository: BreweriesRepository,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) {
    private var parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    private val parentJobs = mutableMapOf<String, Job>()

    operator fun invoke(state: String, page: Int, onResult: (Result<List<Brewery>>) -> Unit) {
        val jobId = "$state::$page"
        parentJobs[jobId] = launchRequest(state, page, onResult)
    }

    private fun launchRequest(
        state: String,
        page: Int,
        onResult: (Result<List<Brewery>>) -> Unit
    ) = scope.launch(dispatcherProvider.computation) {
        val result = breweriesRepository.search(state, page)
        withContext(dispatcherProvider.main) { onResult(result) }
    }

}