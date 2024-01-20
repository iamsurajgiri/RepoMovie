package kutumb.tmdb

import android.app.Application
import kutumb.core.di.moviesModule
import kutumb.tmdb.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieRepo:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieRepo)
            modules(listOf(appModule, moviesModule))
        }
    }
}