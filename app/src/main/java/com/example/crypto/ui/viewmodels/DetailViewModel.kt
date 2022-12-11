package com.example.crypto.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.repositories.CryptoRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val cryptoRepository = CryptoRepository()
    val detail = cryptoRepository.detail

    fun getDetail(id: String) {
        viewModelScope.launch {
            try {
                cryptoRepository.fetchDetail(id)
            } catch (e: Exception) {
                Log.i("DetailViewModel", e.message ?: "null message")
            }
        }
    }
}