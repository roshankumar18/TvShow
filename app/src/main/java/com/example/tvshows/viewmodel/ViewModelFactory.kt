package com.example.tvshows.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvshows.repositoty.MovieRepository

class ViewModelFactory(private val repository: MovieRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }

}