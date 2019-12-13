package com.example.challengeaplaudo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengeaplaudo.helpers.launchAPIRequest
import com.example.challengeaplaudo.interfaces.ServiceInterface
import com.example.challengeaplaudo.models.Product
import retrofit2.await

class MainViewModel(
    private var service: ServiceInterface
): ViewModel() {

    var animeList : MutableLiveData<List<Product>> = MutableLiveData()
    var mangaList : MutableLiveData<List<Product>> = MutableLiveData()

    fun getProducts(){
        loadAnimes()
        loadMangas()
    }

    private fun loadAnimes(){
        launchAPIRequest {
            try{
                val result = service.getProductAnimes().await()
                animeList.postValue(result.data)
            }catch (e: Throwable){
                println(e.message)
            }
        }
    }
    private fun loadMangas(){
        launchAPIRequest {
            try{
                val result = service.getProductManga().await()
                mangaList.postValue(result.data)
            }catch (e: Throwable){
                println(e.message)
            }
        }
    }

}