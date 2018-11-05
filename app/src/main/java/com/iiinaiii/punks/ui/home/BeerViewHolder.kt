package com.iiinaiii.punks.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.iiinaiii.punks.databinding.ItemBeerBinding
import com.iiinaiii.punks.util.glide.GlideApp

class BeerViewHolder(
    private val binding: ItemBeerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: BeerItemUiModel) {
        binding.beerName.text = model.name
        binding.firstBrew.text = model.firstBrewed
        binding.beerAbv.text = model.abv

        GlideApp.with(binding.root.context)
            .load(model.imageUrl)
            .into(binding.beerImage)
    }
}