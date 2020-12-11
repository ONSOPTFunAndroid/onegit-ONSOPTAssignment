package com.hjh96.sopt1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SoptServiceImpl {
    private const val BASE_URL = "http://15.164.83.210:3000"

    private val baseRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val baseService : SoptService = baseRetrofit.create(SoptService:: class.java)
}