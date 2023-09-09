package com.online.jokes.data.impl

import android.content.Context
import android.widget.Toast
import com.online.jokes.data.repo.ToastRepo

class ToastImpl(private val context: Context) : ToastRepo {
    override fun showToast(string: String, duration: Int) {
        Toast.makeText(context, string, duration).show()
    }
}
