package kutumb.tmdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kutumb.core.constansts.Constants
import kutumb.core.data.model.MoviesResponse
import kutumb.core.data.network.ApiResult
import kutumb.core.domain.MoviesRepository

class MainViewModel(
    private val moviesRepository: MoviesRepository
) :ViewModel(){
    private val _movies = MutableLiveData<ApiResult<MoviesResponse>>()
    val movies: LiveData<ApiResult<MoviesResponse>> = _movies
    var currentPage = 1
    //0 for top rated, 1 for popular
    var currentSortType = 0

    fun getTopRatedMovies(){
        _movies.postValue(ApiResult.Loading)
        viewModelScope.launch {
            _movies.postValue(moviesRepository.getTopRatedMovies(Constants.API_KEY,currentPage))
        }
    }
    fun getPopularMovies(){
        _movies.postValue(ApiResult.Loading)
        viewModelScope.launch {
            _movies.postValue(moviesRepository.getPopularMovies(Constants.API_KEY,currentPage))
        }
    }
}