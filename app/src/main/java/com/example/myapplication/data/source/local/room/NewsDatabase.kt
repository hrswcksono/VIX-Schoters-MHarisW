package com.example.myapplication.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.source.local.entity.ArticleEntity


@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase() : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object{
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "News.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}