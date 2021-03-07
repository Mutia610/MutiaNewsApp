package com.mutia.mutianewsapp.Config

import com.mutia.mutianewsapp.Model.ResponseNews
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @GET("top-headlines?country=id&category=technology&apiKey=11f11ea1c9264950b0c88b80e3e5c38a")  //57b8a8101895458fb34ba44bc417b0f6 //11f11ea1c9264950b0c88b80e3e5c38a
    fun getTeknologi(): Call<ResponseNews>

    @GET("top-headlines?country=id&category=business&apiKey=11f11ea1c9264950b0c88b80e3e5c38a")
    fun getBisnis(): Call<ResponseNews>

    @GET("top-headlines?country=id&category=health&apiKey=11f11ea1c9264950b0c88b80e3e5c38a")
    fun getKesehatan(): Call<ResponseNews>

    @GET("top-headlines?country=id&category=sports&apiKey=11f11ea1c9264950b0c88b80e3e5c38a")
    fun getOlahraga(): Call<ResponseNews>
}