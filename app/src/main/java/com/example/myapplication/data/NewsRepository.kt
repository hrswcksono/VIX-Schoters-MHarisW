package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.source.local.LocalDataSource
import com.example.myapplication.data.source.local.entity.ArticleEntity
import com.example.myapplication.data.source.remote.ApiResponse
import com.example.myapplication.data.source.remote.RemoteDataSource
import com.example.myapplication.data.source.remote.response.ArticlesItem
import com.example.myapplication.utils.AppExecutors
import com.example.myapplication.utils.DataMapper
import com.example.myapplication.vo.Resource

class NewsRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : NewsDataSource {

    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(remoteData, localDataSource, appExecutors)
            }
    }

    override fun getNews(): LiveData<Resource<List<ArticleEntity>>> {
        return object : NetworkBoundResource<List<ArticleEntity>, List<ArticlesItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ArticleEntity>> =
                localDataSource.getNews()

            override fun shouldFetch(data: List<ArticleEntity>?) =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getNews()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = ArrayList<ArticleEntity>()
                for (response in data) {
                    newsList.add(DataMapper.mapperArticle(response))
                }
                localDataSource.insertNews(newsList)
            }

        }.asLiveData()
    }

    override fun getBookmarkedNews() =
        localDataSource.getBookmarkedNews()

    override fun setNewsBookmark(news: ArticleEntity, state: Boolean) =
        appExecutors.diskIO().execute{localDataSource.setNewsBookmarked(news, state)}
}