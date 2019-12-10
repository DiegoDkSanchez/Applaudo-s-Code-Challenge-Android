package com.example.challengeaplaudo.controlers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.challengeaplaudo.R
import com.example.challengeaplaudo.helpers.DynamicAdapter
import com.example.challengeaplaudo.helpers.configureList
import kotlinx.android.synthetic.main.main_list_fragment.*

class MainListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadAnimeList()
        loadMangaList()

    }

    private fun loadMangaList() {
        mangaRecycler.configureList(false)
        mangaRecycler.adapter = DynamicAdapter(
            R.layout.product_item,
            listOf("1", "2", "3", "4", "5"),
            action = fun(viewHolder, view, item, position) {

            }
        )
        LinearSnapHelper().attachToRecyclerView(mangaRecycler)
    }

    private fun loadAnimeList() {
        animeRecycler.configureList(false)
        animeRecycler.adapter = DynamicAdapter(
            R.layout.product_item,
            listOf("1", "2", "3", "4", "5"),
            action = fun(viewHolder, view, item, position) {

            }
        )
        LinearSnapHelper().attachToRecyclerView(animeRecycler)
    }

}