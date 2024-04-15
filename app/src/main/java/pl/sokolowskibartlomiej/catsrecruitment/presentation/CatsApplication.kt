package pl.sokolowskibartlomiej.catsrecruitment.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.sokolowskibartlomiej.catsrecruitment.di.databaseModule
import pl.sokolowskibartlomiej.catsrecruitment.di.mainModule
import pl.sokolowskibartlomiej.catsrecruitment.di.networkModule

class CatsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@CatsApplication)
            modules(
                networkModule,
                databaseModule,
                mainModule,
            )
        }
    }
}