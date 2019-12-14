package com.example.challengeaplaudo.controlers

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.bumptech.glide.Glide
import com.example.challengeaplaudo.Constants
import com.example.challengeaplaudo.MainViewModel
import com.example.challengeaplaudo.R
import com.example.challengeaplaudo.helpers.*
import com.example.challengeaplaudo.models.Product
import kotlinx.android.synthetic.main.main_list_fragment.*
import kotlinx.android.synthetic.main.product_item.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainListFragment: Fragment() {

    private val viewmodel : MainViewModel by sharedViewModel()
    private val snapHelperAnime = LinearSnapHelper()
    private val snapHelperManga = LinearSnapHelper()
    private var currentPageAnime = 0
    private var currentPageManga = 0

    private fun loadObservers() {
        viewmodel.animeList.observe(this, Observer { animes ->
            setAnimeConfiguration(animes)
        })
        viewmodel.mangaList.observe(this, Observer { mangas ->
            setMangaConfiguration(mangas)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_list_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadObservers()
        loadListener()
        currentPageAnime = 0
        currentPageManga = 0
        loadingAnime.showLoading()
        loadingManga.showLoading()
        viewmodel.getProducts()
    }

    private fun setAnimeConfiguration(animes : List<Product>){
        animeRecycler.configureList(false)
        if(currentPageAnime == 0) backAnimeButton.visibility = View.GONE
        else backAnimeButton.visibility = View.VISIBLE
        animeRecycler.adapter = createAdapterProducts(animes)
        loadingAnime.stopLoading()
        enableButtons()
        snapHelperAnime.attachToRecyclerView(animeRecycler)
    }
    private fun setMangaConfiguration(mangas : List<Product>){
        mangaRecycler.configureList(false)
        if(currentPageManga == 0) backMangaButton.visibility = View.GONE
        else backMangaButton.visibility = View.VISIBLE
        mangaRecycler.adapter = createAdapterProducts(mangas)
        loadingManga.stopLoading()
        enableButtons()
        snapHelperManga.attachToRecyclerView(mangaRecycler)
    }

    private fun createAdapterProducts(list : List<Product>): DynamicAdapter<Product> {
        return DynamicAdapter(
            R.layout.product_item,
            list,
            action = fun(viewHolder, view, item, position) {
                val uri = item.attributes.posterImage?.medium
                Glide.with(activity)
                    .asBitmap()
                    .load(uri)
                    .apply(GlideConst.CONFIG_GLIDE)
                    .into(view.backgroundItem)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.cardView.transitionName = uri
                }
                if(item.type == Constants.ANIME){
                    view.subtitle.text = item.attributes.episodeCount.toString() + " " + getString(R.string.episodes)
                }else if(item.type == Constants.MANGA){
                    view.subtitle.text = item.attributes.chapterCount.toString() + " " + getString(R.string.chapters)
                }
                view.backgroundItem.scaleType = ImageView.ScaleType.CENTER_CROP
                view.title.text = item.attributes.titles.en_jp
                view.setOnClickListener {
                    val extras = FragmentNavigatorExtras(
                        view.cardView to uri.toString()
                    )
                    val action = MainListFragmentDirections.actionMainListFragmentToDetailProductFragment(uri, product = item)
                    view.findNavController().navigate(action,extras)
                }
            }
        )
    }
    private fun loadListener() {
        searchImg.setOnClickListener {
            disableButtons()
            viewmodel.searchProducts(textSerachEditText.text.toString())
            loadingAnime.showLoading()
            loadingManga.showLoading()
        }
        nextAnimeButton.setOnClickListener {
            disableButtons()
            currentPageAnime += 20
            loadingAnime.showLoading()
            viewmodel.loadAnimes(currentPageAnime)
        }
        backAnimeButton.setOnClickListener {
            disableButtons()
            currentPageAnime -= 20
            loadingAnime.showLoading()
            viewmodel.loadAnimes(currentPageAnime)
        }
        nextMangaButton.setOnClickListener {
            disableButtons()
            currentPageManga += 20
            loadingManga.showLoading()
            viewmodel.loadMangas(currentPageManga)
        }
        backMangaButton.setOnClickListener {
            disableButtons()
            currentPageManga -= 20
            loadingManga.showLoading()
            viewmodel.loadMangas(currentPageManga)
        }
    }

    private fun disableButtons() {
        nextAnimeButton.isEnabled = false
        backAnimeButton.isEnabled = false
        nextMangaButton.isEnabled = false
        backMangaButton.isEnabled = false
        searchImg.isEnabled = false
    }

    private fun enableButtons(){
        nextAnimeButton.isEnabled = true
        backAnimeButton.isEnabled = true
        nextMangaButton.isEnabled = true
        backMangaButton.isEnabled = true
        searchImg.isEnabled = true
    }
}