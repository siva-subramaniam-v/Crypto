package com.example.crypto.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

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

    // New query for most of the details in coin screen:
    // https://api.coingecko.com/api/v3/coins/bitcoin?community_data=false&developer_data=false&sparkline=false

    // coin tickers API - https://api.coingecko.com/api/v3/coins/bitcoin/tickers?include_exchange_logo=false&page=1

    // coin chart data (sparkline) - https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=inr&days=1 (Query params: currency, days)

    @GET("coins/{id}?tickers=false&market_data=false&community_data=false&developer_data=false&sparkline=false")
    suspend fun getDetail(@Path("id") id: String): NetworkCryptoDetail

    @GET("search")
    suspend fun search(
        @Query("query") query: String
    ): SearchResult

    @GET("exchanges/{id}")
    suspend fun getExchangeDetail(@Path("id") id: String): ExchangeDetail


    // TODO: change base url based on run-time
    @Headers(
        "X-RapidAPI-Key: e94cccd0bdmsha32c0871228eb22p182fc3jsn51bec67c0dce",
        "X-RapidAPI-Host: crypto-news14.p.rapidapi.com"
    )
    @GET
    suspend fun getNews(@Url url: String): List<News>
}

object Network {
    val retrofitService: CryptoApiService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }
}