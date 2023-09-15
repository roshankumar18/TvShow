package com.example.tvshows.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshows.R
import com.example.tvshows.adapter.EpisodesAdapter
import com.example.tvshows.adapter.ImageViewPagerAdapter
import com.example.tvshows.databinding.ActivityShowDetailsBinding
import com.example.tvshows.databinding.LayoutEpisodeBottomSheetBinding
import com.example.tvshows.models.Episode
import com.example.tvshows.models.EpisodeInfo
import com.example.tvshows.models.TvShow
import com.example.tvshows.viewmodel.EpisodeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowDetailsBinding
    private lateinit var showId : String
    private lateinit var episodeViewModel: EpisodeViewModel
    private val TAG = "ShowDetailsActivity"
    private lateinit var adapter: ImageViewPagerAdapter
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var episode : List<EpisodeInfo>

    private lateinit var layoutEpisodeBottomSheetBinding: LayoutEpisodeBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        adapter = ImageViewPagerAdapter(emptyList())
        episodeViewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
        intent.getStringExtra("id")?.let { episodeViewModel.getMovie(it) }

        episodeViewModel.episode.observe(this, Observer {
            episode= it.tvShow.episodes
            Log.d(TAG, "onCreate: $episode")
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
        binding.episodeButton.setOnClickListener{
            val dialogView = layoutInflater.inflate(R.layout.layout_episode_bottom_sheet, null)
            bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(dialogView)
            dialogView.findViewById<TextView>(R.id.textTitle).text = intent.getStringExtra("name")
            val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.adapter = EpisodesAdapter(episodes = episode)
            bottomSheetDialog.show()
        }
    }

    private fun setImageAdapter(pictures: List<String>) {
        adapter = ImageViewPagerAdapter(pictures)
        binding.sliderViewPager.adapter =adapter
        binding.progressBar.visibility = View.GONE
    }
}