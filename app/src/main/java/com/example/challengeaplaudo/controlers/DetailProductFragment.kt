package com.example.challengeaplaudo.controlers

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.challengeaplaudo.R
import com.example.challengeaplaudo.helpers.GlideConst
import com.example.challengeaplaudo.models.Product
import kotlinx.android.synthetic.main.detail_product_fragment.*
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.example.challengeaplaudo.Constants
import com.example.challengeaplaudo.helpers.gone
import com.example.challengeaplaudo.helpers.visible


class DetailProductFragment: Fragment() {

    private val args : DetailProductFragmentArgs by navArgs()
    private var product : Product? = null

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
        product = args.product
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardViewDetail.transitionName = args.uri
        }
        Glide.with(activity)
            .asBitmap()
            .load(args.uri)
            .apply(GlideConst.CONFIG_GLIDE)
            .into(coverImage)
        productTittle.text = product?.attributes?.titles?.en_jp
        if(product?.attributes?.youtubeVideoId == null) youtubeVideo.visibility = View.INVISIBLE
        youtubeVideo.setOnClickListener {
            if(product?.attributes?.youtubeVideoId != null) watchYoutubeVideo(product?.attributes?.youtubeVideoId!!)
        }
        typeProductTextView.text = product?.type?.capitalize()
        if(product?.type == Constants.ANIME){
            chaptersOrEpisodesTextView.text = product?.attributes?.episodeCount.toString() + " " + getString(R.string.episodes)
        }else if(product?.type == Constants.MANGA){
            if(product?.attributes?.chapterCount == null) chaptersOrEpisodesTextView.text = getString(R.string.without_chapters_register)
            else chaptersOrEpisodesTextView.text = product?.attributes?.chapterCount.toString() + " " + getString(R.string.chapters)
        }
        ageRatingTextView.text = product?.attributes?.ageRating
        ageRatingGuideTextView.text = product?.attributes?.ageRatingGuide
        averageRatingTextView.text = product?.attributes?.averageRating
        if(product?.attributes?.episodeLength == null) episodeLengthLayout.gone()
        else episodeLengthTextView.text = product?.attributes?.episodeLength.toString() + " " + getString(R.string.min)
        synopsisTextView.text = product?.attributes?.synopsis
        if(product?.attributes?.coverImage?.original == null) coverImageView.gone()
        else {
            Glide.with(activity)
                .asBitmap()
                .load(product?.attributes?.coverImage?.original)
                .apply(GlideConst.CONFIG_GLIDE)
                .into(coverImageView)

        }
    }

    private fun watchYoutubeVideo(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$id")
        )
        try {
            context?.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context?.startActivity(webIntent)
        }

    }

}