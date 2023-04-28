package com.example.tvshows

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshows.databinding.ActivityMainBinding
import com.example.tvshows.paging.MoviePagingAdapter
import com.example.tvshows.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var viewModel: MovieViewModel
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
        adapter = MoviePagingAdapter(this)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.hasFixedSize()
        binding.recyclerView.adapter = adapter

        viewModel.list.observe(this ,Observer{
//            adapter.submitData(lifecycle,it)
            adapter.submitData(lifecycle,it)
        })

    }


}