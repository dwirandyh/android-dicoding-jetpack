package com.dwirandyh.jetpack.data.remote.service

import com.dwirandyh.jetpack.external.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private fun getRetrofit() : Retrofit {
        val httpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiClient(): ApiService = getRetrofit().create(ApiService::class.java)
}