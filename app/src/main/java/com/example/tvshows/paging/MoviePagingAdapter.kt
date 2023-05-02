package com.example.tvshows.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshows.R
import com.example.tvshows.databinding.ItemContainerTvShowBinding
import com.example.tvshows.listener.TvShowsListener
import com.example.tvshows.models.TvShowsInfo

class MoviePagingAdapter(private val context:Context ,private val tvShowsListener: TvShowsListener) : PagingDataAdapter<TvShowsInfo, MoviePagingAdapter.MovieViewHolder>(COMPARATOR) {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemContainerTvShowBinding.bind(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        Log.w(TAG, "onBindViewHolder: $movie", )
        val item = getItem(position)

        if (item != null) {
            holder.binding.textName.text = item.name
            holder.binding.textNetwork.text = item.network
            holder.binding.textStatus.text = item.status
            Glide.with(context).load(item.image_thumbnail_path)
                .into(holder.binding.imageTV)
            holder.itemView.setOnClickListener { tvShowsListener.onTvShowClicked(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_container_tv_show,parent,false)
        return MovieViewHolder(view)
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<TvShowsInfo>() {
            override fun areItemsTheSame(oldItem: TvShowsInfo, newItem: TvShowsInfo): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowsInfo, newItem: TvShowsInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}