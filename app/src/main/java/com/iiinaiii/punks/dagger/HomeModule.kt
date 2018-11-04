package com.iiinaiii.punks.dagger

import androidx.lifecycle.ViewModelProviders
import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import com.iiinaiii.punks.data.beers.BeersRepository
import com.iiinaiii.punks.domain.SearchBeersUseCase
import com.iiinaiii.punks.ui.home.HomeActivity
import com.iiinaiii.punks.ui.home.HomeViewModel
import com.iiinaiii.punks.ui.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger module for [HomeActivity].
 */
@Module(
    includes = [BeersDataModule::class]
)
class HomeModule(private val activity: HomeActivity) {

    @Provides
    fun provideHomeViewModel(
        factory: HomeViewModelFactory
    ): HomeViewModel =
        ViewModelProviders.of(activity, factory).get(HomeViewModel::class.java)

    @Provides
    fun provideHomeViewModelFactory(
        searchBeersUseCase: SearchBeersUseCase,
        coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ): HomeViewModelFactory =
        HomeViewModelFactory(searchBeersUseCase, coroutinesDispatcherProvider)

    @Provides
    fun provideSearchBeersUseCase(
        beersRepository: BeersRepository
    ): SearchBeersUseCase =
        SearchBeersUseCase(beersRepository)
}