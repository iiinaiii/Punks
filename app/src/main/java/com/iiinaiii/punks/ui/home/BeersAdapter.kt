package com.iiinaiii.punks.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iiinaiii.punks.R
import com.iiinaiii.punks.databinding.ItemBeerBinding
import com.iiinaiii.punks.domain.model.Beer
import com.iiinaiii.punks.domain.model.toItemUiModel

class BeersAdapter(
    context: Context,
    val beers: MutableList<Beer> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemBeerBinding = DataBindingUtil.inflate(inflater, R.layout.item_beer, parent, false)
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BeerViewHolder).bind(beers[position].toItemUiModel())
    }

    override fun getItemCount(): Int = beers.size
}