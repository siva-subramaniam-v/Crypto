package com.example.crypto.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.crypto.domain.CryptoDetail
import com.example.crypto.domain.CryptoOverview
import com.example.crypto.network.*
import com.example.crypto.network.SearchResult.*
import kotlinx.coroutines.*

class CryptoRepository() {
    val overview = MutableLiveData<List<CryptoOverview>>()
    val detail = MutableLiveData<CryptoDetail>()
    val coins = MutableLiveData<List<Coin>>()
    val exchanges = MutableLiveData<List<Exchange>>()
    val exchangeDetail = MutableLiveData<ExchangeDetail>()

    suspend fun fetchDetail(id: String) {
        withContext(Dispatchers.IO) {
            detail.postValue(Network.retrofitService.getDetail(id).asDomainModel())
        }
    }

    suspend fun fetchOverview() {
        //withContext(Dispatchers.IO) {
            overview.postValue(
                Network.retrofitService.getOverviewAsync().asDomainModel()
            )
            println(overview.value?.size)
        //}
    }

    suspend fun fetchSearchResults(query: String) {
        withContext(Dispatchers.IO) {
            val searchResult = Network.retrofitService.search(query)
            coins.postValue(searchResult.coins)
            exchanges.postValue(searchResult.exchanges)
        }
    }

    suspend fun fetchExchangeDetail(exchangeId: String) {
        withContext(Dispatchers.IO) {
            exchangeDetail.postValue(Network.retrofitService.getExchangeDetail(exchangeId))
            Log.i("Exchange Repository", exchangeDetail.value?.name ?: "null name")
        }
    }
}