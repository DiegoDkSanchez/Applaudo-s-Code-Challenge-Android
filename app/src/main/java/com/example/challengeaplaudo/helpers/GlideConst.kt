package com.example.challengeaplaudo.helpers

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object GlideConst {
    val CONFIG_GLIDE = RequestOptions()
        //.placeholder(R.drawable.)
        .priority(Priority.HIGH)
        .format(DecodeFormat.PREFER_RGB_565)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .dontAnimate()
        .fitCenter()
}