package com.example.crypto.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.repositories.CryptoRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val cryptoRepository = CryptoRepository()
    val coins = cryptoRepository.coins
    val exchanges = cryptoRepository.exchanges

    fun search(query: String) {
        viewModelScope.launch {
            try {
                cryptoRepository.fetchSearchResults(query)
            } catch (e: Exception) {
                Log.i("SearchViewModel", e.message ?: "null message")
                //e.printStackTrace()
            }
        }
    }
}