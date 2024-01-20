package kutumb.core.di

import com.google.gson.GsonBuilder
import kutumb.core.constansts.Constants
import kutumb.core.data.network.MoviesService
import kutumb.core.data.repository.MoviesRepositoryImpl
import kutumb.core.domain.MoviesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moviesModule = module {
    val gson = GsonBuilder().setLenient().create()

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(MoviesService::class.java)
    }

    single<MoviesRepository> {MoviesRepositoryImpl(moviesService = get()) }


}