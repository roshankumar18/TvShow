package com.example.tvshows.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tvshows.adapter.ImageViewPagerAdapter
import com.example.tvshows.databinding.ActivityShowDetailsBinding
import com.example.tvshows.models.TvShow
import com.example.tvshows.viewmodel.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowDetailsBinding
    private lateinit var showId : String
    private lateinit var episodeViewModel: EpisodeViewModel
    private val TAG = "ShowDetailsActivity"
    private lateinit var adapter: ImageViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        adapter = ImageViewPagerAdapter(emptyList())
        episodeViewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
        intent.getStringExtra("id")?.let { episodeViewModel.getMovie(it) }
        episodeViewModel.episode.observe(this, Observer {
            setImageAdapter(it.tvShow.pictures)
            initialize(it.tvShow)
        })

    }

    private fun initialize(tvShow: TvShow) {
        Glide.with(this).load(tvShow.image_path).into(binding.imageTvShow)
        binding.textName.text = tvShow.name
        binding.textNetworkCountry.text = tvShow.network
        binding.textStatus.text = tvShow.status
        binding.textStarted.text = "Started On :${tvShow.start_date}"
        binding.textDescription.text = tvShow.description
        binding.textReadMore.setOnClickListener {
            if (binding.textReadMore.text.equals("Read more")){
                binding.textDescription.maxLines = Int.MAX_VALUE
                binding.textReadMore.text = "Read less"
            }else{
                binding.textDescription.maxLines = 4
                binding.textReadMore.text = "Read more"
            }
        }
    }

    private fun setImageAdapter(pictures: List<String>) {
        adapter = ImageViewPagerAdapter(pictures)
        binding.sliderViewPager.adapter =adapter
        binding.progressBar.visibility = View.GONE

    }
}