package com.example.tvshows.network

import com.example.tvshows.models.Episode
import com.example.tvshows.models.TVShows
import retrofit2.http.GET
import retrofit2.http.Query


interface TvApiService {
    @GET("most-popular")
    suspend fun getMovies(
        @Query("page") page: Int
    ) :TVShows

    @GET("show-details")
    suspend fun getEpisode(
        @Query("q") id : String
    ) : Episode
}