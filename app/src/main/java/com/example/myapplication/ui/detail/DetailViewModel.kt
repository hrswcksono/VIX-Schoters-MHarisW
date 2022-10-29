package com.example.myapplication.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.NewsRepository
import com.example.myapplication.data.source.local.entity.ArticleEntity

class DetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun setBookmarkedNews(news: ArticleEntity, newState: Boolean) {
        newsRepository.setNewsBookmark(news, newState)
    }
}