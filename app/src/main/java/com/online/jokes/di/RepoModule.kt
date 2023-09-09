package com.online.jokes.di

import com.online.jokes.data.impl.JokeImpl
import com.online.jokes.data.impl.StorageImpl
import com.online.jokes.data.impl.StringImpl
import com.online.jokes.data.impl.ToastImpl
import com.online.jokes.data.model.JokeModel
import com.online.jokes.data.repo.JokeRepo
import com.online.jokes.data.repo.StorageRepo
import com.online.jokes.data.repo.StringRepo
import com.online.jokes.data.repo.ToastRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object RepoModule {
    val module = module {
        factory<JokeRepo> { JokeImpl(get()) }
        factory<StorageRepo<JokeModel>> { StorageImpl(get()) }
        factory<ToastRepo> { ToastImpl(androidContext()) }
        factory<StringRepo> { StringImpl(androidContext()) }
    }
}
