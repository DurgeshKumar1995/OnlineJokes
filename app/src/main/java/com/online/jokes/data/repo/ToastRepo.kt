package com.online.jokes.data.repo

import android.widget.Toast
import androidx.annotation.IntDef
import androidx.annotation.IntRange

interface ToastRepo {
    @IntDef(Toast.LENGTH_LONG, Toast.LENGTH_SHORT)
    @IntRange(from = 1)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Duration

    fun showToast(string: String, @Duration duration: Int)
}
