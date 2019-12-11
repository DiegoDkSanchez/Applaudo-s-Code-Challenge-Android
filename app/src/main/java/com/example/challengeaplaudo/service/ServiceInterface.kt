package com.example.challengeaplaudo.service

import com.example.challengeaplaudo.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceInterface {
    @GET("anime")
   suspend fun getProductAnimes(): Response<ProductResponse>

    @GET("manga")
    suspend fun getProductManga(): Response<ProductResponse>
}