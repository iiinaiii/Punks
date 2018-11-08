package com.iiinaiii.punks.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import com.iiinaiii.punks.domain.GetBeerUseCase

class BeerDetailViewModelFactory(
    private val beerId: Int,
    private val getBeerUseCase: GetBeerUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != BeerDetailViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return BeerDetailViewModel(
            beerId,
            getBeerUseCase,
            dispatcherProvider
        ) as T
    }
}