package com.example.challengeaplaudo.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.configureList(isVerticar: Boolean){
    this.setHasFixedSize(true)
    if(isVerticar){
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }else{
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}