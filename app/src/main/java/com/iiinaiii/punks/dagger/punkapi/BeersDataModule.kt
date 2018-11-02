package com.iiinaiii.punks.dagger.punkapi

import com.iiinaiii.punks.BuildConfig
import com.iiinaiii.punks.punkapi.data.BeersRepository
import com.iiinaiii.punks.punkapi.data.search.BeersSearchService
import com.iiinaiii.punks.punkapi.data.search.SearchRemoteDataSource
import com.iiinaiii.punks.dagger.CoroutinesDispatcherProviderModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Dagger module providing classes required to beers with data.
 */
@Module(includes = [CoroutinesDispatcherProviderModule::class])
class BeersDataModule {

    @Provides
    fun provideBeersRepository(
        remoteDataSource: SearchRemoteDataSource
    ) = BeersRepository.getInstance(remoteDataSource)

    @Provides
    fun provideBeersSearchService(moshi: Moshi): BeersSearchService {
        return provideRetrofit(
            BeersSearchService.ENDPOINT,
            moshi
        ).create(BeersSearchService::class.java)
    }

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        moshi: Moshi
    ): Retrofit {
        return provideRetrofitBuilder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideRetrofitBuilder() = Retrofit.Builder()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

}