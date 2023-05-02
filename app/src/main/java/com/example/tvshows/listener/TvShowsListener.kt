package com.example.tvshows.listener

import com.example.tvshows.models.TvShowsInfo

interface TvShowsListener {
    fun onTvShowClicked(tvShowsInfo: TvShowsInfo)
}