package com.example.crypto.repository

import androidx.lifecycle.MutableLiveData
import com.example.crypto.domain.CryptoDetail
import com.example.crypto.domain.CryptoOverview
import com.example.crypto.network.*
import kotlinx.coroutines.*

class CryptoRepository() {
    val overview = MutableLiveData<List<CryptoOverview>>()
    val detail = MutableLiveData<CryptoDetail>()

    suspend fun fetchDetail(id: String) {
        withContext(Dispatchers.IO) {
            detail.postValue(Network.retrofitService.getDetail(id).asDomainModel())
        }
    }

    suspend fun fetchOverview() {
        withContext(Dispatchers.IO) {
            overview.postValue(
                Network.retrofitService.getOverviewAsync().asDomainModel()
            )
            println(overview.value?.size)
        }
    }
}