package com.online.jokes.di

import com.online.jokes.domain.AddJokeListLocal
import com.online.jokes.domain.ClearJokeListLocal
import com.online.jokes.domain.GetJoke
import com.online.jokes.domain.GetJokeListLocal
import com.online.jokes.domain.StringProvideUseCase
import com.online.jokes.domain.ToastCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CaseModule {
    const val ioDispatcherKey = "IODispatcher"
    const val mainDispatcherKey = "MainDispatcher"
    val module = module {
        single(named(ioDispatcherKey)) {
            Dispatchers.IO
        }
        factory { GetJoke(get(), get(named(ioDispatcherKey)), get(), get()) }
        factory { AddJokeListLocal(get()) }
        factory { ClearJokeListLocal(get()) }
        factory { GetJokeListLocal(get()) }
        factory { StringProvideUseCase(get()) }
        factory { ToastCase(get()) }
    }
}
