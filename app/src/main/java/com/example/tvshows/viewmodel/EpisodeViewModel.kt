package com.example.tvshows.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshows.models.Episode
import com.example.tvshows.repositoty.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EpisodeViewModel @Inject constructor(private val episodeRepository: EpisodeRepository ) :
    ViewModel() {
//    init {
//        viewModelScope.launch {
//            episodeRepository.getMovie("12565")
//        }
//    }

    fun getMovie(id:String){
        viewModelScope.launch {
            episodeRepository.getMovie(id)
        }
    }
    val episode :LiveData<Episode>
    get() =  episodeRepository.episode
}