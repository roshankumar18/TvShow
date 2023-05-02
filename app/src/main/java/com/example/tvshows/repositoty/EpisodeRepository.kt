package com.example.tvshows.repositoty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshows.models.Episode
import com.example.tvshows.network.TvApiService
import retrofit2.http.Query
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val service : TvApiService) {

    private val episodeObject  = MutableLiveData<Episode>()
    val episode : LiveData<Episode>
    get() = episodeObject

    suspend fun getMovie(query: String) : Episode{
        val response = service.getEpisode(query)

        if (response!=null){
            episodeObject.postValue(response)
        }
        return response
    }
}