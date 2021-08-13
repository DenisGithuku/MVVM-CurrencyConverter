package com.denisgithuku.mvvmcurrencyconverter.data.repository

import com.denisgithuku.mvvmcurrencyconverter.data.models.CurrencyResponse
import com.denisgithuku.mvvmcurrencyconverter.main.CurrencyApiService
import com.denisgithuku.mvvmcurrencyconverter.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApiService
) : MainRepository{
    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            }else {
                Resource.Error(response.message())
            }
        }catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }


}