package com.example.myapplication.ui.bookmark

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.NewsRepository

class BookmarkViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()
}