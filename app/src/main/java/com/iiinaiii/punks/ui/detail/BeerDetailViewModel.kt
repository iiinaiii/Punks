package com.iiinaiii.punks.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import com.iiinaiii.punks.data.Result
import com.iiinaiii.punks.domain.GetBeerUseCase
import com.iiinaiii.punks.domain.model.Beer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BeerDetailViewModel(
    beerId: Int,
    private val getBeer: GetBeerUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    private val _uiModel = MutableLiveData<BeerDetailUiModel>()
    val uiModel: LiveData<BeerDetailUiModel>
        get() = _uiModel

    init {
        loadBeer(beerId)
    }

    private fun loadBeer(beerId: Int) = scope.launch(dispatcherProvider.computation) {
        val result = getBeer(beerId)
        when (result) {
            is Result.Success -> {
                emitUiModel(result.data)
            }
            is Result.Error -> throw result.exception
        }
    }

    private fun emitUiModel(beer: Beer) {
        _uiModel.postValue(BeerDetailUiModel(beer))
    }

    data class BeerDetailUiModel(
        val beer: Beer
    )
}