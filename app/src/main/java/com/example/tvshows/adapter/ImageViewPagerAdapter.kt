package com.example.tvshows.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshows.R
import com.example.tvshows.databinding.ImageItemLayoutBinding

class ImageViewPagerAdapter(private val images :List<String>) : RecyclerView.Adapter<ImageViewPagerAdapter.ImageViewPageHolder>() {

    class ImageViewPageHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ImageItemLayoutBinding.bind(itemView)
        fun setImage(url:String){
            Glide.with(binding.root.context).load(url).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewPageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item_layout,parent,false)
        return ImageViewPageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewPageHolder, position: Int) {
        holder.setImage(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

}