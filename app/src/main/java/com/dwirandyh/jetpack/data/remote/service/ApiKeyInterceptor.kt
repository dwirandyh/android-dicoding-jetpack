package com.dwirandyh.jetpack.data.remote.service

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val apiKey = "99bc66d74f8686fc34d985741a078dc0"
        val url = original.url().newBuilder().addQueryParameter("api_key", apiKey).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}