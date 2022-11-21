package com.example.crypto.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.repository.CryptoRepository
import kotlinx.coroutines.launch

class OverviewViewModel(application: Application) : AndroidViewModel(application) {
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