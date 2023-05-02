package com.example.tvshows.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshows.R
import com.example.tvshows.databinding.ItemContainerTvShowBinding
import com.example.tvshows.models.TvShowsInfo

class MovieAdapter(private val context :Context,private var movie :List<TvShowsInfo>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val TAG = "RecyclerView"
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemContainerTvShowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_container_tv_show,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.w(TAG, "onBindViewHolder: $movie", )
        val item = movie[position]
        holder.binding.textName.text = item.name
        holder.binding.textNetwork.text = item.network
        holder.binding.textStatus.text = item.status
        Glide.with(context).load(item.image_thumbnail_path)
            .into(holder.binding.imageTV)

    }
    fun updateData(newData :List<TvShowsInfo>){
        movie = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.w("itemCount", "getItemCount: ${movie.size}", )
        return movie.size
    }
}