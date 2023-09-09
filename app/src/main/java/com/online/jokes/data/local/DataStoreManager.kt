package com.online.jokes.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.lang.reflect.Type

class DataStoreManager<T> (
    private val gson: Gson,
    private val type: Type,
    private val dataStore: DataStore<Preferences>,
    private val preferenceKey: Preferences.Key<String>,
) {

    companion object {
        const val JOKE_DATASTORE = "joke_datastore"
        val DataList = stringPreferencesKey("DataList")
        private const val EMPTY_JSON_STRING = "[]"
        private const val OPERATION_SUCCESS = 200
        private const val TAG = "DataStoreManager"
    }

    fun getAll(): Flow<List<T>> {
        return dataStore.data.map { preferences ->
            val jsonString = preferences[preferenceKey] ?: EMPTY_JSON_STRING
            val elements = gson.fromJson<List<T>>(jsonString, type)
            elements
        }
    }

    fun insert(data: List<T>): Flow<Int> {
        return channelFlow {
            dataStore.edit {
                val jsonString = gson.toJson(data, type)
                Timber.tag(TAG).d("insert::$jsonString")
                it[preferenceKey] = jsonString
                send(OPERATION_SUCCESS)
            }
        }
    }

    fun get(where: (T) -> Boolean): Flow<T> {
        return getAll().map { cachedData ->
            cachedData.first(where)
        }
    }

    fun clearAll(): Flow<Int> {
        return flow {
            dataStore.edit {
                it.remove(preferenceKey)
                emit(OPERATION_SUCCESS)
            }
        }
    }
}
