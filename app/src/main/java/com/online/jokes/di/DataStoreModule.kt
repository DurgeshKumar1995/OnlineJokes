package com.online.jokes.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.reflect.TypeToken
import com.online.jokes.data.local.DataStoreManager
import com.online.jokes.data.model.JokeModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreManager.JOKE_DATASTORE)

object DataStoreModule {

    val module = module {
        single<DataStoreManager<JokeModel>> {
            DataStoreManager<JokeModel>(
                gson = get(),
                type = object : TypeToken<List<JokeModel>>() {}.type,
                preferenceKey = DataStoreManager.DataList,
                dataStore = androidContext().dataStore,
            )
        }
    }
}
