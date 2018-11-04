package com.iiinaiii.punks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import com.iiinaiii.punks.data.Result
import com.iiinaiii.punks.domain.SearchBeersUseCase
import com.iiinaiii.punks.domain.model.Beer
import com.iiinaiii.punks.util.DataLoadingSubject
import com.iiinaiii.punks.util.event.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val searchBeers: SearchBeersUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), DataLoadingSubject {

    private val _uiModel = MutableLiveData<HomeUiModel>()
    val uiModel: LiveData<HomeUiModel>
        get() = _uiModel
    override var isDataLoading = false
    private var isLastPageLoaded = false

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    private var pageNum = 1

    fun loadBeers() {
        if (isLastPageLoaded) {
            return
        }
        getBeers(pageNum)
    }

    private fun getBeers(page: Int) = scope.launch(dispatcherProvider.computation) {
        withContext(dispatcherProvider.main) { showLoading() }
        isDataLoading = true

        val result = searchBeers(page)
        withContext(dispatcherProvider.main) {
            if (result is Result.Success) {
                pageNum++
                emitUiModel(showSuccess = Event(BeerResultUiModel(result.data)))
            } else {
                emitUiModel(showError = Event(Unit))
                isLastPageLoaded = true
            }
            isDataLoading = false
        }
    }

    private fun showLoading() {
        emitUiModel(showProgress = true)
    }

    private fun emitUiModel(
        showProgress: Boolean = false,
        showSuccess: Event<BeerResultUiModel>? = null,
        showError: Event<Unit>? = null
    ) {
        _uiModel.value = HomeUiModel(showProgress, showSuccess, showError)
    }
}

data class HomeUiModel(
    val showProgress: Boolean,
    val showSuccess: Event<BeerResultUiModel>?,
    val showError: Event<Unit>?
)

data class BeerResultUiModel(
    val beers: List<Beer>
)