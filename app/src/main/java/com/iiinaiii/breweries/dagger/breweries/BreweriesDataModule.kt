package com.iiinaiii.breweries.dagger.breweries

import com.iiinaiii.breweries.BuildConfig
import com.iiinaiii.breweries.breweries.data.BreweriesRepository
import com.iiinaiii.breweries.breweries.data.search.BreweriesSearchService
import com.iiinaiii.breweries.breweries.data.search.SearchRemoteDataSource
import com.iiinaiii.breweries.dagger.CoroutinesDispatcherProviderModule
import com.iiinaiii.breweries.data.CoroutinesDispatcherProvider
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
 * Dagger module providing classes required to breweries with data.
 */
@Module(includes = [CoroutinesDispatcherProviderModule::class])
class BreweriesDataModule {

    @Provides
    fun provideBreweriesRepository(
        remoteDataSource: SearchRemoteDataSource,
        dispatcherProvider: CoroutinesDispatcherProvider
    ) = BreweriesRepository.getInstance(remoteDataSource, dispatcherProvider)

    @Provides
    fun provideBreweriesSearchService(moshi: Moshi): BreweriesSearchService {
        return provideRetrofit(
            BreweriesSearchService.ENDPOINT,
            moshi
        ).create(BreweriesSearchService::class.java)
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
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
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