package com.denisgithuku.mvvmcurrencyconverter.data.repository

import com.denisgithuku.mvvmcurrencyconverter.data.models.CurrencyResponse
import com.denisgithuku.mvvmcurrencyconverter.util.Resource

interface MainRepository {
    suspend fun getRates(base: String): Resource<CurrencyResponse>
}