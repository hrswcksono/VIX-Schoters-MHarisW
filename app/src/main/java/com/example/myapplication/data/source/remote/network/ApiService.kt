package com.example.myapplication.data.source.remote.network

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.source.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("everything?domains=wsj.com&apiKey=${BuildConfig.API_KEY}")
    fun getList(): Call<NewsResponse>
}