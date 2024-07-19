package id.eve.mygithubapp

import android.app.Application
import com.xridwan.mygithub.di.prefsModule
import com.xridwan.mygithub.di.useCaseModule
import com.xridwan.mygithub.di.viewModelModule
import id.eve.core.di.dataSourceModule
import id.eve.core.di.databaseModule
import id.eve.core.di.networkModule
import id.eve.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    prefsModule
                )
            )
        }
    }
}