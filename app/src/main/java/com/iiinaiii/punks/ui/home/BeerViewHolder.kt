package com.iiinaiii.punks.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.iiinaiii.punks.databinding.ItemBeerBinding
import com.iiinaiii.punks.util.glide.GlideApp

class BeerViewHolder(
    private val binding: ItemBeerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: BeerItemUiModel) {
        binding.beerName.text = model.name

        GlideApp.with(binding.root.context)
            .load(model.imageUrl)
            .into(binding.beerImage)
    }
}