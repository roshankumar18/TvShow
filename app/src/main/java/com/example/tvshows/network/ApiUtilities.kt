package com.example.tvshows.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {
    private val URL = "https://www.episodate.com/api/"
    fun getInstance() :Retrofit{
        val loggingInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}