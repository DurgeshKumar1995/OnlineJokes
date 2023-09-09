package com.online.jokes.data.impl

import android.content.Context
import com.online.jokes.data.repo.NetworkRepo
import com.online.jokes.utils.NetworkUtils

class NetworkImpl(private val context: Context) : NetworkRepo {
    override fun isConnected(): Boolean {
        return NetworkUtils.isNetworkAvailable(context)
    }
}
