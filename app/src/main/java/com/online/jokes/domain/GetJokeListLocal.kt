package com.online.jokes.domain

import com.online.jokes.data.model.JokeModel
import com.online.jokes.data.repo.StorageRepo
import kotlinx.coroutines.flow.Flow

class GetJokeListLocal(private val repo: StorageRepo<JokeModel>) {
    operator fun invoke(): Flow<List<JokeModel>> {
        return repo.getAll()
    }
}
