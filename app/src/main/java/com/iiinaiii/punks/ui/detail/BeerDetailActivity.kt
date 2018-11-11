package com.iiinaiii.punks.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iiinaiii.punks.R
import com.iiinaiii.punks.dagger.inject
import com.iiinaiii.punks.databinding.ActivityBeerDetailBinding
import com.iiinaiii.punks.domain.model.Beer
import com.iiinaiii.punks.ui.detail.item.HeaderItem
import com.iiinaiii.punks.ui.detail.item.SimpleTextItem
import com.iiinaiii.punks.ui.detail.item.ValueItem
import com.iiinaiii.punks.util.delegates.contentView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class BeerDetailActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModel: BeerDetailViewModel

    private val binding by contentView<BeerDetailActivity, ActivityBeerDetailBinding>(
        R.layout.activity_beer_detail
    )
    private val groupAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val beerId = intent.getIntExtra(EXTRA_BEER_ID, -1)
        check(beerId != -1) {
            "Beer ID is invalid"
        }

        inject(beerId)

        binding.setLifecycleOwner(this)
        setupRecyclerView()
        binding.viewModel = viewModel
        viewModel.uiModel.observe(this, Observer { uiModel ->
            binding.uiModel = uiModel
            updateBeerRecyclerData(uiModel.beer)
        })
    }

    private fun setupRecyclerView() {
        binding.detailRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@BeerDetailActivity)
            isNestedScrollingEnabled = false
            adapter = groupAdapter
        }
    }

    private fun updateBeerRecyclerData(beer: Beer) {
        // Description
        val descriptionSection = Section().apply {
            setHeader(HeaderItem(getString(R.string.header_description)))
            add(SimpleTextItem(beer.description))
        }

        // Basics
        val basicsSection = Section().apply {
            setHeader(HeaderItem(getString(R.string.header_basics)))
            add(ValueItem(getString(R.string.volume), beer.volume))
            add(ValueItem(getString(R.string.boil_volume), beer.boilVolume))
            add(ValueItem(getString(R.string.abv), beer.abv))
            add(ValueItem(getString(R.string.target_fg), beer.targetFg))
            add(ValueItem(getString(R.string.target_fg), beer.targetFg))
            add(ValueItem(getString(R.string.ebc), beer.ebc))
            add(ValueItem(getString(R.string.srm), beer.srm))
            add(ValueItem(getString(R.string.ph), beer.ph))
            add(ValueItem(getString(R.string.attenuation_level), beer.attenuationLevel))
        }

        // Food pairing
        val foodPairingSection = Section().apply {
            setHeader(HeaderItem(getString(R.string.header_food_pairing)))
            addAll(
                beer.foodPairing.map { SimpleTextItem(it) }
            )
        }

        // Brewer's tips
        val tipsSection = Section().apply {
            setHeader(HeaderItem(getString(R.string.header_brewers_tips)))
            add(SimpleTextItem(beer.brewersTips))
        }

        groupAdapter.apply {
            add(descriptionSection)
            add(basicsSection)
            add(foodPairingSection)
            add(tipsSection)
        }
    }

    companion object {
        private const val EXTRA_BEER_ID = "extra_beer_id"

        fun createIntent(context: Context, beerId: Int): Intent {
            return Intent(context, BeerDetailActivity::class.java)
                .putExtra(EXTRA_BEER_ID, beerId)
        }
    }
}
