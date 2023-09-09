package com.online.jokes.di

import com.online.jokes.persentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ViewModelModule {
    val module = module {
        single(named(CaseModule.mainDispatcherKey)) {
            Dispatchers.Main
        }
        viewModel {
            MainViewModel(
                get(),
                get(),
                get(),
                get(),
                get(named(CaseModule.ioDispatcherKey)),
                get(named(CaseModule.mainDispatcherKey)),
            )
        }
    }
}
