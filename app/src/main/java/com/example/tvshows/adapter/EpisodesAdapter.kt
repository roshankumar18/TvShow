package com.example.tvshows.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.R
import com.example.tvshows.databinding.ItemContainerEpisodeBinding
import com.example.tvshows.models.EpisodeInfo

class EpisodesAdapter(val episodes : List<EpisodeInfo>) : RecyclerView.Adapter<EpisodesAdapter.EpisodeHolder>() {
    class EpisodeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemContainerEpisodeBinding.bind(itemView)
        fun bindEpisode(episodeInfo: EpisodeInfo){
            var title = "S"
            var episodeNumber = episodeInfo.episode.toString()
            if(episodeInfo.season.toString().length == 1){
                title+="0${episodeInfo.season}"
            }
            if (episodeNumber.length==1){
                episodeNumber += "0$episodeNumber"
            }
            val season = episodeInfo.season.toString()

            binding.episode.text = episodeInfo.name
            binding.title.text =  title+episodeNumber
            binding.airDate.text = episodeInfo.air_date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_container_episode,parent,false)
        return EpisodeHolder(item)
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.bindEpisode(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }
}