package com.iiinaiii.punks.dagger

import com.iiinaiii.punks.data.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.android.Main

/**
 * Provide [CoroutinesDispatcherProvider] to this app's components.
 */
@Module
class CoroutinesDispatcherProviderModule {
    @Provides
    fun provideCoroutinesDispatcherProvider() = CoroutinesDispatcherProvider(
        Dispatchers.Main, Dispatchers.Default, Dispatchers.IO
    )
}