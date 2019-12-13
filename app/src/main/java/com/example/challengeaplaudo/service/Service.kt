package com.example.challengeaplaudo.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Service {

    private val BASE_URL = "https://kitsu.io/api/edge/"
    private var productListener: ProductListener? = null

    fun setProductListener(listener: ProductListener){
        productListener = listener
    }

    fun makeRetrofitService(): ServiceInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ServiceInterface::class.java)
    }

    fun getAnimes(){
        val service = makeRetrofitService()
        println("Servicio animes")
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getProductAnimes()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val productResponse = response.body()
                        productListener?.animeListener(productResponse?.data)
                        println("ANIMES succes")
                    } else {
                        println("ANIMES fail")
                    }
                } catch (e: HttpException) {
                    println(e)
                } catch (e: Throwable) {
                    println(e)
                }
            }
        }
    }

    fun getMangas(){
        val service = makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getProductManga()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val productResponse = response.body()
                        productListener?.mangaListener(productResponse?.data)
                    } else {

                    }
                } catch (e: HttpException) {
                    println(e)
                } catch (e: Throwable) {
                    println(e)
                }
            }
        }
    }


}