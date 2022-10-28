package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.source.local.entity.ArticleEntity
import com.example.myapplication.vo.Resource

interface NewsDataSource {

    fun getNews() : LiveData<Resource<List<ArticleEntity>>>
    fun getBookmarkedNews() : LiveData<List<ArticleEntity>>
    fun setNewsBookmark(news: ArticleEntity, state: Boolean)
}