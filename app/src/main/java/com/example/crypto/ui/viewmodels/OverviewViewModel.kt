package com.example.crypto.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.repositories.CryptoRepository
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private val cryptoRepository = CryptoRepository()
    val overview = cryptoRepository.overview

    init {
        viewModelScope.launch {
            try {
                cryptoRepository.fetchOverview()
            } catch (e: Exception) {
                Log.i("OverviewViewModel", e.message ?: "null message")
            }
        }
    }
}