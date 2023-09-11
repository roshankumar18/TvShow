package com.example.tvshows.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
        private val URL = "https://www.episodate.com/api/"
    @Singleton
    @Provides
    fun getRetrofit():Retrofit{
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30,TimeUnit.MINUTES)
            .writeTimeout(30,TimeUnit.MINUTES)
            .readTimeout(30,TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder().baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getMovies(retrofit: Retrofit):TvApiService{
        return retrofit.create(TvApiService::class.java)
    }


}