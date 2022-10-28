package com.example.myapplication.data.source.local

import com.example.myapplication.data.source.local.entity.ArticleEntity
import com.example.myapplication.data.source.local.room.NewsDao

class LocalDataSource private constructor(private val newsDao: NewsDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(newsDao: NewsDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(newsDao).apply {
                INSTANCE = this
            }
    }

    fun getNews() = newsDao.getNews()
    fun insertNews(article: List<ArticleEntity>) = newsDao.insertNews(article)
    fun getBookmarkedNews() = newsDao.getBookmarkedNews()
    fun setNewsBookmarked(news: ArticleEntity, newState: Boolean) {
        news.bookmarked = newState
        newsDao.updateNews(news)
    }
}