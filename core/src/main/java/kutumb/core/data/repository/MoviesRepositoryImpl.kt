package kutumb.core.data.repository

import kutumb.core.data.model.MoviesResponse
import kutumb.core.data.network.ApiResult
import kutumb.core.data.network.MoviesService
import kutumb.core.domain.MoviesRepository

class MoviesRepositoryImpl(private val moviesService: MoviesService): MoviesRepository {
    override suspend fun getTopRatedMovies(apiKey: String, pageNum: Int): ApiResult<MoviesResponse> {
        return try {
            val response = moviesService.getTopRatedMovies(apiKey, pageNum)

            if (response.isSuccessful){
                response.body()?.let {
                    ApiResult.Success(it)
                }?: ApiResult.Error(response.message()?:"Something went wrong")
            }else{
                ApiResult.Error(response.message())
            }
        }catch (e: Exception){
            ApiResult.Error(e.localizedMessage?: "Something went wrong")
        }
    }

    override suspend fun getPopularMovies(apiKey: String, pageNum: Int): ApiResult<MoviesResponse> {
        return try {
            val response = moviesService.getPopularMovies(apiKey, pageNum)

            if (response.isSuccessful){
                response.body()?.let {
                    ApiResult.Success(it)
                }?: ApiResult.Error(response.message()?:"Something went wrong")
            }else{
                ApiResult.Error(response.message())
            }
        }catch (e: Exception){
            ApiResult.Error(e.localizedMessage?: "Something went wrong")
        }
    }
}