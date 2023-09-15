package com.example.tvshows.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tvshows.models.TvShow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM TvShow")
    fun getShow(): LiveData<List<TvShow>>

    @Insert
    fun insertAllShows(show: TvShow)

}