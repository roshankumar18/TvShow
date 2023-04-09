package com.example.tvshows.repositoty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshows.models.TVShow
import com.example.tvshows.models.TvShowX
import com.example.tvshows.network.TvApiService

class MovieRepository(private val service : TvApiService) {
    private val movieObject  = MutableLiveData<List<TvShowX>>()
    val movie : LiveData<List<TvShowX>>
    get() = movieObject

    suspend fun getMovie(page:Int){
        val response = service.getMovies(page)
        if (response.body()!=null){
            movieObject.postValue(response.body()!!.tv_shows)
        }
    }
}