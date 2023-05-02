package com.example.tvshows.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tvshows.models.TvShowsInfo
import com.example.tvshows.network.TvApiService

class MoviePagingSource(val movies : TvApiService) :PagingSource<Int,TvShowsInfo>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShowsInfo> {
        try {
            val position = params.key ?: 1
            val reponse = movies.getMovies(position)
            return LoadResult.Page(
                data = reponse.tv_shows,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == reponse.pages) null else position + 1
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvShowsInfo>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1 )
        }
    }


}