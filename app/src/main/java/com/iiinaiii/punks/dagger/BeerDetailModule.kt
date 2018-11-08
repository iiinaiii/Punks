package com.iiinaiii.punks.dagger

import androidx.lifecycle.ViewModelProviders
import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import com.iiinaiii.punks.data.beers.BeersRepository
import com.iiinaiii.punks.domain.GetBeerUseCase
import com.iiinaiii.punks.ui.detail.BeerDetailActivity
import com.iiinaiii.punks.ui.detail.BeerDetailViewModel
import com.iiinaiii.punks.ui.detail.BeerDetailViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger module for [BeerDetailActivity].
 */
@Module(
    includes = [BeersDataModule::class]
)
class BeerDetailModule(private val beerId: Int, private val activity: BeerDetailActivity) {

    @Provides
    fun provideBeerDetailViewModel(
        factory: BeerDetailViewModelFactory
    ): BeerDetailViewModel =
        ViewModelProviders.of(activity, factory).get(BeerDetailViewModel::class.java)

    @Provides
    fun provideBeerDetailViewModelFactory(
        getBeerUseCase: GetBeerUseCase,
        coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ): BeerDetailViewModelFactory =
        BeerDetailViewModelFactory(beerId, getBeerUseCase, coroutinesDispatcherProvider)

    @Provides
    fun provideGetBeersUseCase(
        beersRepository: BeersRepository
    ): GetBeerUseCase =
        GetBeerUseCase(beersRepository)
}