package com.example.myapplication.utils

import com.example.myapplication.data.source.local.entity.ArticleEntity
import com.example.myapplication.data.source.remote.response.ArticlesItem

object DataMapper {
    fun mapperArticle(from : ArticlesItem) = ArticleEntity(
        title = from.title,
        publishedAt = from.publishedAt,
        author = from.author,
        urlToImage = from.urlToImage,
        description = from.description,
        url = from.url,
        content = from.content,
        bookmarked = false
    )
}