package kutumb.tmdb.di

import kutumb.tmdb.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    viewModel{MainViewModel(moviesRepository = get())}
}