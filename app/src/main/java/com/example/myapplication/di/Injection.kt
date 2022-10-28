package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.NewsDataSource
import com.example.myapplication.data.NewsRepository
import com.example.myapplication.data.source.local.LocalDataSource
import com.example.myapplication.data.source.local.room.NewsDatabase
import com.example.myapplication.data.source.remote.RemoteDataSource
import com.example.myapplication.data.source.remote.network.ApiConfig
import com.example.myapplication.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val database = NewsDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.newsDao())
        val appExecutors = AppExecutors()

        return NewsRepository.getInstance(remoteDataSource,localDataSource, appExecutors)
    }
}