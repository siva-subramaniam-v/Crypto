package com.example.crypto.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.crypto.network.Network
import com.example.crypto.network.asDatabaseModel
import com.example.crypto.database.DbNews
import com.example.crypto.database.NewsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val database: NewsDatabase) {
    val dbNews: LiveData<List<DbNews>> = database.newsDao.getAll()

    suspend fun fetchNews() {
        withContext(Dispatchers.IO) {
            Log.i("NewsRepository", "News being fetched  by repository")
            val news = Network.retrofitService.getNews("https://crypto-news14.p.rapidapi.com/news/cointelegraph")
            Log.i("NewsRepository", "News fetched")
            database.newsDao.insertAll(*news.asDatabaseModel())
        }
    }
}