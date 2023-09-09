package com.online.jokes.data.impl

import com.online.jokes.data.local.DataStoreManager
import com.online.jokes.data.model.JokeModel
import com.online.jokes.data.repo.StorageRepo
import kotlinx.coroutines.flow.Flow

class StorageImpl(private val dataStoreManager: DataStoreManager<JokeModel>) : StorageRepo<JokeModel> {
    override fun insert(data: List<JokeModel>): Flow<Int> {
        return dataStoreManager.insert(data)
    }

    override fun get(where: (JokeModel) -> Boolean): Flow<JokeModel> {
        return dataStoreManager.get(where)
    }

    override fun getAll(): Flow<List<JokeModel>> {
        return dataStoreManager.getAll()
    }

    override fun clearAll(): Flow<Int> {
        return dataStoreManager.clearAll()
    }
}
