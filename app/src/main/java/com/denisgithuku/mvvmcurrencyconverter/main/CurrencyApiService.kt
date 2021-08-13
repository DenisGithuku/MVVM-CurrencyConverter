package com.denisgithuku.mvvmcurrencyconverter.main

import com.denisgithuku.mvvmcurrencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyApiService {
    @GET("/latest?access_key=0bd60f90bf0f3b9081269a63554b8d6b&format=1")
    suspend fun getRates(
        @Query("base") base: String
    ):
            Response<CurrencyResponse>
}

