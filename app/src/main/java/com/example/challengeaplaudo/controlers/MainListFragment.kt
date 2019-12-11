package com.example.challengeaplaudo.controlers

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.bumptech.glide.Glide
import com.example.challengeaplaudo.MainViewModel
import com.example.challengeaplaudo.R
import com.example.challengeaplaudo.helpers.DynamicAdapter
import com.example.challengeaplaudo.helpers.GlideConst
import com.example.challengeaplaudo.helpers.configureList
import com.example.challengeaplaudo.models.Product
import kotlinx.android.synthetic.main.main_list_fragment.*
import kotlinx.android.synthetic.main.product_item.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainListFragment: Fragment() {

    private val viewmodel : MainViewModel by sharedViewModel()

    private fun loadObservers() {
        viewmodel.animeList.observe(this, Observer { animes ->
            loadAnimeList(animes)
        })
        viewmodel.mangaList.observe(this, Observer { mangas ->
            loadMangaList(mangas)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_list_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("Se empieza a cargar todo")
        viewmodel.loadAnimes()
        viewmodel.loadMangas()
        loadObservers()
    }

    private fun loadMangaList(mangas : List<Product>) {
        mangaRecycler.configureList(false)
        mangaRecycler.adapter = DynamicAdapter(
            R.layout.product_item,
            mangas,
            action = fun(viewHolder, view, item, position) {
                Glide.with(activity)
                    .asBitmap()
                    .load(Uri.parse(item.attributes.posterImage?.medium))
                    .apply(GlideConst.CONFIG_GLIDE)
                    .into(view.backgroundItem)
                view.backgroundItem.scaleType = ImageView.ScaleType.CENTER_CROP
                view.title.text = item.attributes.titles.en_jp
                view.subtitle.text = item.attributes.chapterCount.toString() + " " + getString(R.string.chapters)
            }
        )
        LinearSnapHelper().attachToRecyclerView(mangaRecycler)
    }

    private fun loadAnimeList(animes : List<Product>) {
        animeRecycler.configureList(false)
        animeRecycler.adapter = DynamicAdapter(
            R.layout.product_item,
            animes,
            action = fun(viewHolder, view, item, position) {
                Glide.with(activity)
                    .asBitmap()
                    .load(Uri.parse(item.attributes.posterImage?.medium))
                    .apply(GlideConst.CONFIG_GLIDE)
                    .into(view.backgroundItem)
                view.backgroundItem.scaleType = ImageView.ScaleType.CENTER_CROP
                view.title.text = item.attributes.titles.en_jp
                view.subtitle.text = item.attributes.episodeCount.toString() + " " + getString(R.string.episodes)
            }
        )
        LinearSnapHelper().attachToRecyclerView(animeRecycler)
    }

}