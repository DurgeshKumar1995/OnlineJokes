package com.online.jokes.data.repo

import androidx.annotation.StringRes

interface StringRepo {
    fun getString(@StringRes id: Int): String
}
