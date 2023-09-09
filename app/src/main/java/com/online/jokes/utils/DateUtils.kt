package com.online.jokes.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    fun getReadableTime(time: Long): String {
        return Date(time).toSimpleString()
    }
}

@SuppressLint("SimpleDateFormat")
private fun Date.toSimpleString(): String {
    val format = SimpleDateFormat("MMM dd, yyyy 'at' hh:mm:ss a")
    return format.format(this)
}
