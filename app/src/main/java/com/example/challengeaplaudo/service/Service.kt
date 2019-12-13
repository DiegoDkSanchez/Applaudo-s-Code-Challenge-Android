package com.example.challengeaplaudo.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Service {

    val BASE_URL = "https://kitsu.io/api/edge/"

    inline fun <reified T> getRetrofit(): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(T::class.java)
    }

}