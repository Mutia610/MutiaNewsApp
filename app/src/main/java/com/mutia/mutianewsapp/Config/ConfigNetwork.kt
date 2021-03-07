package com.mutia.mutianewsapp.Config

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigNetwork {
    fun interceptor(): OkHttpClient{
        val inteceptor  = HttpLoggingInterceptor()
        inteceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(inteceptor).build()
    }

    fun configNet():Retrofit{
        return Retrofit.Builder().baseUrl("http://newsapi.org/v2/").client(interceptor())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun service() = configNet().create(NewsService::class.java)

}