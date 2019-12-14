package com.example.challengeaplaudo.interfaces

import com.example.challengeaplaudo.models.ProductResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ServiceInterface {
    @GET("anime/")
    suspend fun getProductAnimes(): Response<ProductResponse>

//    Call<ItemResponse> getItems(@Query("param1") String param1, @Query("param2") String param2, @Query("param3") String param3);

    @GET("manga/")
    suspend fun getProductManga(): Response<ProductResponse>
}