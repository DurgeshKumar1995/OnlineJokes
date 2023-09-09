package com.online.jokes.di

import com.online.jokes.data.impl.NetworkImpl
import com.online.jokes.data.repo.NetworkRepo
import com.online.jokes.domain.InternetConnectionCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object InternetConnectionModule {
    val module = module {
        single<NetworkRepo> { NetworkImpl(androidContext()) }
        single { InternetConnectionCase(get()) }
    }
}
