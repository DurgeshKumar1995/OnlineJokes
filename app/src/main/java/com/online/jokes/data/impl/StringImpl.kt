package com.online.jokes.data.impl

import android.content.Context
import androidx.annotation.StringRes
import com.online.jokes.data.repo.StringRepo

class StringImpl(private val context: Context) : StringRepo {
    override fun getString(@StringRes id: Int): String {
        return context.resources.getString(id)
    }
}
