package com.iiinaiii.punks.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import com.iiinaiii.punks.domain.SearchBeersUseCase

class HomeViewModelFactory(
    private val searchBeersUseCase: SearchBeersUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != HomeViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return HomeViewModel(
            searchBeersUseCase,
            dispatcherProvider
        ) as T
    }

}