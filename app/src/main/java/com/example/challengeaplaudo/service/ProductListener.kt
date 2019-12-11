package com.example.challengeaplaudo.service

import com.example.challengeaplaudo.models.Product

interface ProductListener {
    fun animeListener(animes: List<Product>?)
    fun mangaListener(mangas: List<Product>?)
}