package com.online.jokes.domain

import android.widget.Toast
import com.online.jokes.data.repo.ToastRepo

class ToastCase(private val repo: ToastRepo) {
    operator fun invoke(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        repo.showToast(msg, duration)
    }
}
