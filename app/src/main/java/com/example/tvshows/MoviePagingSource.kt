package com.example.tvshows

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tvshows.models.TvShowX
import com.example.tvshows.repositoty.MovieRepository

class MoviePagingSource(val repository: MovieRepository) : PagingSource<Int, TvShowX>() {
    override fun getRefreshKey(state: PagingState<Int, TvShowX>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShowX> {
        return try{
            val position = params.key ?: 1
            val result = repository.getMovie(position)
            LoadResult.Page(
                data = result.tv_shows,
                prevKey = if(position == 1) null else position-1,
                nextKey = if (position==(result.total).toInt()) null else position+1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}