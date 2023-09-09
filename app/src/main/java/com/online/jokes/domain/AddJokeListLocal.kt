package com.online.jokes.domain

import com.online.jokes.data.model.JokeModel
import com.online.jokes.data.repo.StorageRepo
import kotlinx.coroutines.flow.Flow

class AddJokeListLocal(private val repo: StorageRepo<JokeModel>) {
    operator fun invoke(data: List<JokeModel>): Flow<Int> {
        return repo.insert(data)
    }
}
