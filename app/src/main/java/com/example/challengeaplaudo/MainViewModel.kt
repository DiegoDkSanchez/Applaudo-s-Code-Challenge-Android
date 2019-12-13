package com.example.challengeaplaudo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengeaplaudo.models.Product
import com.example.challengeaplaudo.service.ProductListener
import com.example.challengeaplaudo.service.Service

class MainViewModel(private var service: Service): ViewModel() {

    var animeList : MutableLiveData<List<Product>> = MutableLiveData()
    var mangaList : MutableLiveData<List<Product>> = MutableLiveData()

    init {
        service.setProductListener(object : ProductListener{
            override fun mangaListener(mangas: List<Product>?) {
                mangaList.postValue(mangas)
            }
            override fun animeListener(animes: List<Product>?) {
               animeList.postValue(animes)
            }
        })
    }

    fun loadAnimes(){
        service.getAnimes()
    }
    fun loadMangas(){
        service.getMangas()
    }

}