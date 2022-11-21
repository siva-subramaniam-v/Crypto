package com.example.crypto.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.repository.CryptoRepository
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val cryptoRepository = CryptoRepository()
    val detail = cryptoRepository.detail

    fun getDetail(id: String) {
        viewModelScope.launch {
            cryptoRepository.fetchDetail(id)
        }
    }
}