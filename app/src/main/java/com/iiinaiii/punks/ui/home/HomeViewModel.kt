package com.iiinaiii.punks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import com.iiinaiii.punks.data.Result
import com.iiinaiii.punks.domain.SearchBeersUseCase
import com.iiinaiii.punks.domain.model.Beer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val searchBeers: SearchBeersUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _uiModel = MutableLiveData<HomeUiModel>()
    val uiModel: LiveData<HomeUiModel>
        get() = _uiModel

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    private var pageNum = 1

    fun loadBeers() {
        getBeers(pageNum)
    }

    private fun getBeers(page: Int) = scope.launch(dispatcherProvider.computation) {
        val result = searchBeers(page)
        if (result is Result.Success) {
            pageNum++
            withContext(dispatcherProvider.main) { emitUiModel(result.data) }
        }
    }

    private fun emitUiModel(beers: List<Beer>) {
        _uiModel.value = HomeUiModel(beers)
    }
}

data class HomeUiModel(
    val beers: List<Beer>
)