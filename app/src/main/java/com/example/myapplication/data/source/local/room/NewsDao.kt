package com.example.myapplication.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.source.local.entity.ArticleEntity


@Dao
interface NewsDao {

    @Query("SELECT * FROM newsentities")
    fun getNews(): LiveData<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<ArticleEntity>)

    @Query("SELECT * FROM newsentities where bookmarked = 1")
    fun getBookmarkedNews(): LiveData<List<ArticleEntity>>

    @Update
    fun updateNews(news: ArticleEntity)
}