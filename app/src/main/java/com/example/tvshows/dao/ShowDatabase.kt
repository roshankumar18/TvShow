package com.example.tvshows.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tvshows.models.TvShow

@Database(entities = [TvShow::class], version = 1)
abstract class ShowDatabase : RoomDatabase() {
    abstract fun showDao() : TvShowDao
}