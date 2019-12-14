package com.example.challengeaplaudo.service

import com.example.challengeaplaudo.interfaces.ProductListener
import com.example.challengeaplaudo.interfaces.ServiceInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class Service {

    private val BASE_URL = "https://kitsu.io/api/edge/"
    private var productListener: ProductListener? = null

    fun setProductListener(listener: ProductListener){
        productListener = listener
    }

    fun makeRetrofitService(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())

    }

    fun getAnimes(currentPages: Int?, text: String?){
        val service = makeRetrofitService()
        try{
            if(currentPages != null){
                service.client(createInterceptopAddPages(currentPages))
            }else if(text.toString().trim().isNotEmpty()){
                service.client(createInterceptopSearch(text.toString()))
            }
        }catch (e: Exception){
            println(e)
        }
        val call = service.build().create(ServiceInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = call.getProductAnimes()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val productResponse = response.body()
                        productListener?.animeListener(productResponse?.data)
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

    fun getMangas(currentPages: Int?, text: String?){
        val service = makeRetrofitService()
        try {
            if(currentPages != null){
                service.client(createInterceptopAddPages(currentPages))
            }else if(text.toString().trim().isNotEmpty()){
                service.client(createInterceptopSearch(text.toString()))
            }
        }catch (e: Exception){
            println(e)
        }
        val call = service.build().create(ServiceInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = call.getProductManga()
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

    fun createInterceptopAddPages(pages: Int): OkHttpClient{
        try {
            var builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.addNetworkInterceptor(Interceptor {
                var request = it.request()
                val httpUrl = request.url().newBuilder()
                    .addQueryParameter("page[limit]", "20")
                    .addQueryParameter("page[offset]", "$pages").build()
                request = request.newBuilder().url(httpUrl).build()
                return@Interceptor it.proceed(request)
            })

            return builder.build()
        }catch (e: Exception){
            println(e)
        }
        return OkHttpClient()
    }

    fun createInterceptopSearch(text: String): OkHttpClient{
        try {
            var builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.addNetworkInterceptor(Interceptor {
                var request = it.request()
                val httpUrl = request.url().newBuilder()
                    .addQueryParameter("filter[text]", "$text").build()
                request = request.newBuilder().url(httpUrl).build()
                return@Interceptor it.proceed(request)
            })
            return builder.build()
        }catch (e: Exception){
            println(e)
        }
        return OkHttpClient()
    }


}