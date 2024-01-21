package kutumb.tmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kutumb.core.data.model.Movie
import kutumb.core.data.network.ApiResult
import kutumb.tmdb.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter
    private val mainViewModel: MainViewModel by viewModel()
    private var movies: MutableList<Movie> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //toolbar
        setSupportActionBar(binding.toolbar)
        adapter = MoviesAdapter()
        binding.moviesRv.adapter = adapter

        mainViewModel.getTopRatedMovies()
        binding.moviesRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //due to time constraint, I am not using pagination library as well as triggering api call when user reaches to the end of the list
                //In real project, I would have used pagination library and triggered api call when user was about to reach the end of the list
                if (!recyclerView.canScrollVertically(1)) {
                    mainViewModel.currentPage += mainViewModel.currentPage
                    if (mainViewModel.currentSortType == 0){
                        mainViewModel.getTopRatedMovies()
                    }else{
                        mainViewModel.getPopularMovies()
                    }
                }
            }
        })
        initObservers()


    }

    private fun initObservers() {
        mainViewModel.movies.observe(this){
            when(it){
                is ApiResult.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isGone = true
                }
                ApiResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is ApiResult.Success -> {
                    movies.addAll(it.data.results)
                    adapter.moviesDiffer.submitList(movies)
                    binding.progressBar.isGone = true
                    binding.moviesRv.isVisible = true
                    if (mainViewModel.currentPage == 1){
                        binding.moviesRv.scrollToPosition(0)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.top ->{
                movies = mutableListOf()
                mainViewModel.currentSortType = 0
                mainViewModel.currentPage = 1
                mainViewModel.getTopRatedMovies()
                true
            }
            R.id.popular ->{
                movies = mutableListOf()
                mainViewModel.currentSortType = 1
                mainViewModel.currentPage = 1
                mainViewModel.getPopularMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}