package com.online.jokes

import android.app.Application
import com.online.jokes.di.CaseModule
import com.online.jokes.di.DataStoreModule
import com.online.jokes.di.InternetConnectionModule
import com.online.jokes.di.NetworkModule
import com.online.jokes.di.RepoModule
import com.online.jokes.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

/* ktlint-enable parameter-list-wrapping */
class OnlineJokeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OnlineJokeApplication) // add app context
            logger(AndroidLogger()) // Default Koin logger
            modules(NetworkModule.module) // Network Module
            modules(RepoModule.module) // Repository Module
            modules(CaseModule.module) // Domain Module
            modules(ViewModelModule.module) // ViewModel Module
            modules(DataStoreModule.module) // Local Storage Module
            modules(InternetConnectionModule.module) // Internet connection Module
        }
    }
}
