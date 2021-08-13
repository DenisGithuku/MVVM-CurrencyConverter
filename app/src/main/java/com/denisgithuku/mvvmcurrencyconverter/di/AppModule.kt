package com.denisgithuku.mvvmcurrencyconverter.di

import com.denisgithuku.mvvmcurrencyconverter.data.repository.DefaultMainRepository
import com.denisgithuku.mvvmcurrencyconverter.data.repository.MainRepository
import com.denisgithuku.mvvmcurrencyconverter.main.CurrencyApiService
import com.denisgithuku.mvvmcurrencyconverter.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "http://api.exchangeratesapi.io/v1"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApiService::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(apiService: CurrencyApiService): MainRepository = DefaultMainRepository(apiService)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val default: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}