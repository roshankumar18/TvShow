package com.example.tvshows.models

data class TVShow(
    val page: Int,
    val pages: Int,
    val total: String,
    val tv_shows: List<TvShowX>
)