package com.online.jokes.domain

import androidx.annotation.StringRes
import com.online.jokes.data.repo.StringRepo

class StringProvideUseCase(private val repo: StringRepo) {
    operator fun invoke(@StringRes id: Int): String {
        return repo.getString(id)
    }
}
