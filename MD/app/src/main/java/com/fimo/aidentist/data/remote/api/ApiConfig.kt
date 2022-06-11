package com.fimo.aidentist.data.remote.api

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    //Url that connect to the API
    private var base_url = "https://aidentist-352309.et.r.appspot.com/"

    //Logging Interceptor
    private fun onlyLoggingOnDebug(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }

    //Get API Service
    fun getApiService(): ApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(onlyLoggingOnDebug())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}