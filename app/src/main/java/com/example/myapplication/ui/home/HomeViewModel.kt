package com.example.myapplication.ui.home

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.NewsRepository

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getNews() = newsRepository.getNews()
}