package com.example.tvshows.network

import com.example.tvshows.models.TVShow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvApiService {
    @GET("most-popular")
    suspend fun getMovies(
        @Query("page") page: Int
    ) :Response<TVShow>
}