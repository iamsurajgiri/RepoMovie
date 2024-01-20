package kutumb.core.data.network

import kutumb.core.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey:String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey:String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

}