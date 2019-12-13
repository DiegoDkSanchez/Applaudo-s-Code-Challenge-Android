package com.example.challengeaplaudo.controlers

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.challengeaplaudo.R
import com.example.challengeaplaudo.helpers.GlideConst
import kotlinx.android.synthetic.main.detail_product_fragment.*
import kotlinx.android.synthetic.main.product_item.view.*

class DetailProductFragment: Fragment() {

    val args : DetailProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_product_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("MIRA VOSSSS")
        println(args)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            coverImage.transitionName = args.uri
        }
        Glide.with(activity)
            .asBitmap()
            .load(args.uri)
            .apply(GlideConst.CONFIG_GLIDE)
            .into(coverImage)
    }

}