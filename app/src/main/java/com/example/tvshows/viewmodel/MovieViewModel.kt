package com.example.tvshows.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tvshows.repositoty.MovieRepository
import com.example.tvshows.models.TvShowX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getMovie(1)
//        }
//    }
//
//    val movie :LiveData<List<TvShowX>>
//    get() =  repository.movie
    val list = repository.getMovies().cachedIn(viewModelScope)


}