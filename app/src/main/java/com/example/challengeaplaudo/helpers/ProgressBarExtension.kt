package com.example.challengeaplaudo.helpers

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.showLoading(){
    this.visibility = View.VISIBLE
}

fun ProgressBar.stopLoading(){
    this.visibility = View.GONE
}