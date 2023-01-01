package uz.akfadiler.testappaliftech.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.akfadiler.testappaliftech.BuildConfig

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: App
            private set
    }

}