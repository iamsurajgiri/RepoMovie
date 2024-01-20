package kutumb.core.domain

import kutumb.core.data.model.MoviesResponse
import kutumb.core.data.network.ApiResult

interface MoviesRepository {

    suspend fun getTopRatedMovies(apiKey: String, pageNum: Int): ApiResult<MoviesResponse>

    suspend fun getPopularMovies(apiKey: String, pageNum: Int): ApiResult<MoviesResponse>

}