package com.example.tvshows

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshows.activity.ShowDetailsActivity
import com.example.tvshows.databinding.ActivityMainBinding
import com.example.tvshows.listener.TvShowsListener
import com.example.tvshows.models.TvShowsInfo
import com.example.tvshows.paging.LoaderAdapter
import com.example.tvshows.paging.MoviePagingAdapter
import com.example.tvshows.repositoty.EpisodeRepository
import com.example.tvshows.viewmodel.EpisodeViewModel
import com.example.tvshows.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , TvShowsListener{
    private val TAG = "MainActivity"
    private lateinit var viewModel: MovieViewModel
//    private lateinit var episodeViewModel: EpisodeViewModel
    private lateinit var binding: ActivityMainBinding
//    private lateinit var adapter: MovieAdapter
    var isFetchingMore : Boolean= false
    var page :Int = 1
    private lateinit var adapter: MoviePagingAdapter


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.progressBar.visibility = View.VISIBLE
//        val service = ApiUtilities.getInstance().create(TvApiService::class.java)
//        val repository = MovieRepository(service)
//        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(MovieViewModel::class.java)
//        adapter = MovieAdapter(this , emptyList())
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        viewModel.movie.observe(this, Observer {
//           adapter.updateData(it)
//            binding.progressBar.visibility = View.GONE
//            binding.recyclerView.visibility = View.VISIBLE
//            Log.w(TAG, "onCreate: $it")
//        })
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.VISIBLE
        adapter = MoviePagingAdapter(this,this)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.hasFixedSize()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(LoaderAdapter())

        viewModel.list.observe(this ,Observer{
            adapter.submitData(lifecycle,it)
            binding.recyclerView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        })

        
    }

    override fun onTvShowClicked(tvShowsInfo: TvShowsInfo) {
        val intent = Intent(this,ShowDetailsActivity::class.java)
        intent.putExtra("id",tvShowsInfo.id.toString())
        intent.putExtra("name",tvShowsInfo.name.toString())
        startActivity(intent)
    }


}