package com.example.tvshows.repositoty

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.tvshows.network.TvApiService
import com.example.tvshows.paging.MoviePagingSource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service : TvApiService) {

    fun getMovies() = Pager(
        config = PagingConfig(pageSize = 20 , maxSize = 100),
        pagingSourceFactory = {MoviePagingSource(service)}
    ).liveData


//    private val movieObject  = MutableLiveData<List<TvShowX>>()
//    val movie : LiveData<List<TvShowX>>
//    get() = movieObject
//
//    suspend fun getMovie(page:Int) : TVShow{
//        val response = service.getMovies(page)
//        if (response.body()!=null){
//            movieObject.postValue(response.body()!!.tv_shows)
//        }
//        return response.body()!!
//    }
}