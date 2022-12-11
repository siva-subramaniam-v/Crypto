package com.example.crypto.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class DbNews(
    val title: String,
    val imgSrcUrl: String,
    @PrimaryKey
    val url: String,
    val date: String
)