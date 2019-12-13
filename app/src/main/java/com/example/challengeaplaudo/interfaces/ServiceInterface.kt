package com.example.challengeaplaudo.interfaces

import com.example.challengeaplaudo.models.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ServiceInterface {
    @GET("anime/")
    fun getProductAnimes(): Call<ProductResponse>

    @GET("manga/")
    fun getProductManga(): Call<ProductResponse>
}