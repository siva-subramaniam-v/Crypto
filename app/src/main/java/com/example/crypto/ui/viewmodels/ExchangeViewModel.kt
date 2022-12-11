package com.example.crypto.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.repositories.CryptoRepository
import kotlinx.coroutines.launch

class ExchangeViewModel: ViewModel() {
    private val cryptoRepository = CryptoRepository()
    val exchangeDetail = cryptoRepository.exchangeDetail

    fun getExchangeDetail(exchangeId: String) {
        viewModelScope.launch {
            try {
                cryptoRepository.fetchExchangeDetail(exchangeId)
                Log.i("ExchangeViewModel", "Fetching exchange Details")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("ExchangeViewModel", e.message ?: "null message")
            }
        }
    }
}