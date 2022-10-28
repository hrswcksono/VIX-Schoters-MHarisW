package com.example.myapplication.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.source.remote.network.ApiService
import com.example.myapplication.data.source.remote.response.ArticlesItem
import com.example.myapplication.data.source.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource private constructor(private val apiService: ApiService){

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getNews(): LiveData<ApiResponse<List<ArticlesItem>>> {
        val result = MutableLiveData<ApiResponse<List<ArticlesItem>>>()

        val client = apiService.getList()

        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val data = response.body()?.articles
                result.value = if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                result.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }

        })

        return result
    }
}