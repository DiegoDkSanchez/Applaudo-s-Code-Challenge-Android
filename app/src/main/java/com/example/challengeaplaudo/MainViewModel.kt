package com.example.challengeaplaudo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengeaplaudo.helpers.launchAPIRequest
import com.example.challengeaplaudo.interfaces.ProductListener
import com.example.challengeaplaudo.interfaces.ServiceInterface
import com.example.challengeaplaudo.models.Product
import com.example.challengeaplaudo.service.Service
import retrofit2.await

class MainViewModel(private var service: Service): ViewModel() {

    var animeList : MutableLiveData<List<Product>> = MutableLiveData()
    var mangaList : MutableLiveData<List<Product>> = MutableLiveData()

    init {
        service.setProductListener(object : ProductListener {
            override fun mangaListener(mangas: List<Product>?) {
                mangaList.postValue(mangas)
            }
            override fun animeListener(animes: List<Product>?) {
                animeList.postValue(animes)
            }
        })
    }

    fun getProducts(){
        service.getAnimes(0, null)
        service.getMangas(0, null)
    }

    fun loadAnimes(currentPages: Int){
        service.getAnimes(currentPages, null)
    }
    fun loadMangas(currentPages: Int){
        service.getMangas(currentPages, null)
    }
    fun searchProducts(text: String){
        service.getAnimes(null, text)
        service.getMangas(null, text)
    }

}