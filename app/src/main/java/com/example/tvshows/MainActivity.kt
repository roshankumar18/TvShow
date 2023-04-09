package com.example.tvshows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshows.adapter.MovieAdapter
import com.example.tvshows.databinding.ActivityMainBinding
import com.example.tvshows.network.ApiUtilities
import com.example.tvshows.network.TvApiService
import com.example.tvshows.repositoty.MovieRepository
import com.example.tvshows.viewmodel.MovieViewModel
import com.example.tvshows.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.VISIBLE
        val service = ApiUtilities.getInstance().create(TvApiService::class.java)
        val repository = MovieRepository(service)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(MovieViewModel::class.java)
        adapter = MovieAdapter(this , emptyList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.movie.observe(this, Observer {
           adapter.updateData(it)
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            Log.w(TAG, "onCreate: $it", )
        })

    }


}