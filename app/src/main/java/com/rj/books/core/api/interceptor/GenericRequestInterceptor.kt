package com.rj.books.core.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class GenericRequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("Intercepting: ${request.url().url()}, Method: ${request.method()}")
        println("Headers: ${request.headers()}")
        println("Body: ${request.body()}")

        return chain.proceed(request)
    }
}