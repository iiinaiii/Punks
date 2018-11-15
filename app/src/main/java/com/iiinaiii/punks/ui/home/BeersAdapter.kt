package com.iiinaiii.punks.ui.home

import android.app.Activity
import android.app.ActivityOptions
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iiinaiii.punks.R
import com.iiinaiii.punks.databinding.ItemBeerBinding
import com.iiinaiii.punks.ui.detail.BeerDetailActivity

class BeersAdapter(
    private val host: Activity,
    val beers: MutableList<BeerItemUiModel> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(host)
        val binding: ItemBeerBinding = DataBindingUtil.inflate(inflater, R.layout.item_beer, parent, false)
        return BeerViewHolder(binding) { data ->
            openBeerDetail(data)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BeerViewHolder).bind(beers[position])
    }

    override fun getItemCount(): Int = beers.size

    private fun openBeerDetail(data: BeerViewHolder.TransitionData) {
        val activityOptions = ActivityOptions.makeSceneTransitionAnimation(host, *data.sharedElements)
        host.startActivity(BeerDetailActivity.createIntent(host, data.beer.id), activityOptions.toBundle())
    }
}