package com.online.jokes.data.repo

import kotlinx.coroutines.flow.Flow

interface StorageRepo<T> {
    fun insert(data: List<T>): Flow<Int>

    fun get(where: (T) -> Boolean): Flow<T>

    fun getAll(): Flow<List<T>>

    fun clearAll(): Flow<Int>
}
