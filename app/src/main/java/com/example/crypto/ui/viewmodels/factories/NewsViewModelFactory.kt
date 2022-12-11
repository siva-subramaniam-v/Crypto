package com.example.crypto.ui.viewmodels.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crypto.ui.viewmodels.NewsViewModel

class NewsViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java))
            return NewsViewModel(application) as T
        throw java.lang.IllegalArgumentException("unknown viewModel class")
    }
}