package com.example.crypto.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.database.getDatabase
import com.example.crypto.repositories.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(application: Application): AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val newsRepository = NewsRepository(database)
    val news = newsRepository.dbNews

    init {
        viewModelScope.launch {
            Log.i("NewsViewModel", "Fetching news...")
            newsRepository.fetchNews()
        }
    }
}