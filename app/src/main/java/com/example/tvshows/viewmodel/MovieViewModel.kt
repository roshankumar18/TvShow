package com.example.tvshows.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshows.repositoty.MovieRepository
import com.example.tvshows.models.TvShowX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovie(1)
        }
    }

    val movie :LiveData<List<TvShowX>>
    get() =  repository.movie

}