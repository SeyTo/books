package com.rj.books.core.api.interceptor

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class ReAuthInterceptor(val context: Context):
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}