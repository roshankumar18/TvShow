package com.example.tvshows.models

data class TVShows(
    val page: Int,
    val pages: Int,
    val total: String,
    val tv_shows: List<TvShowsInfo>
)