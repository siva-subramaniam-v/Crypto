package com.example.crypto.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.coingecko.com/api/v3/"

private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CryptoApiService {
    @GET("coins/markets?vs_currency=inr&order=market_cap_desc&per_page=250&page=1&sparkline=false&price_change_percentage=24h")
    suspend fun getOverviewAsync():
            List<NetworkCryptoOverView>

    @GET("coins/{id}?tickers=false&market_data=false&community_data=false&developer_data=false&sparkline=false")
    suspend fun getDetail(@Path("id") id:String): NetworkCryptoDetail
}

object Network {
    val retrofitService: CryptoApiService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }
}