package com.iiinaiii.punks.domain

import com.iiinaiii.punks.punkapi.data.BeersRepository
import com.iiinaiii.punks.punkapi.data.Result
import com.iiinaiii.punks.punkapi.data.api.model.Beer
import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchBeersUseCase @Inject constructor(
    private val beersRepository: BeersRepository,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) {
    private var parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    private val parentJobs = mutableMapOf<String, Job>()

    operator fun invoke(state: String, page: Int, onResult: (Result<List<Beer>>) -> Unit) {
        val jobId = "$state::$page"
        parentJobs[jobId] = launchRequest(page, onResult)
    }

    private fun launchRequest(
        page: Int,
        onResult: (Result<List<Beer>>) -> Unit
    ) = scope.launch(dispatcherProvider.computation) {
        val result = beersRepository.search(page)
        withContext(dispatcherProvider.main) { onResult(result) }
    }

}