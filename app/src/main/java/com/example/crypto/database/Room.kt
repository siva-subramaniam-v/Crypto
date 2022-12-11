package com.example.crypto.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAll(): LiveData<List<DbNews>> // check why suspend fun causes error

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dbNews: DbNews)
}

@Database(
    entities = [DbNews::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}

private lateinit var INSTANCE: NewsDatabase

fun getDatabase(context: Context): NewsDatabase {
    synchronized(NewsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news"
            ).build()
        }
    }

    return INSTANCE
}