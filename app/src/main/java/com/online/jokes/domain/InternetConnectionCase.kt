package com.online.jokes.domain

import com.online.jokes.data.repo.NetworkRepo

class InternetConnectionCase(private val repo: NetworkRepo) {
    operator fun invoke(): Boolean {
        return repo.isConnected()
    }
}
