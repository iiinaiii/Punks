package com.iiinaiii.breweries.dagger

import com.iiinaiii.breweries.data.CoroutinesDispatcherProvider
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.android.Main

/**
 * Provide [CoroutinesDispatcherProvider] to this app's components.
 */
class CoroutinesDispatcherProviderModule {
    @Provides
    fun provideCoroutinesDispatcherProvider() = CoroutinesDispatcherProvider(
        Dispatchers.Main, Dispatchers.Default, Dispatchers.IO)
}